package a.entity.gus06.jdbc.gui.analyze1;

import a.framework.*;
import java.sql.Connection;
import javax.swing.JPanel;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20230223";}
	
	public static final String COL1_DB = "TABLE_SCHEMA";
	public static final String COL1_TABLE = "TABLE_NAME";
	public static final String COL1_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL1_COLUMN_TYPE = "COLUMN_TYPE";
	public static final String COL1_COLUMN_KEY = "COLUMN_KEY";
	public static final String COL1_IS_NULLABLE = "IS_NULLABLE";
	public static final String COL1_EXTRA = "EXTRA";
	
	public static final String COL2_TABLE_NAME = "TABLE_NAME";
	public static final String COL2_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL2_REFERENCED_TABLE_NAME = "REFERENCED_TABLE_NAME";
	public static final String COL2_REFERENCED_COLUMN_NAME = "REFERENCED_COLUMN_NAME";
	
	public static final String COL3_TABLE_NAME = "TABLE_NAME";
	public static final String COL3_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL3_CONSTRAINT_NAME = "CONSTRAINT_NAME";

	public static final String KEY1_COL_TYPES = "col_types";



	private Service findCol;
	private Service findFk;
	private Service findUk;
	private Service count;
	private Service countFkOrphans;
	private Service threadManager;
	private Service tab;
	private Service buildDataTable;
	private Service buildDataColumn;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	
	private Thread t;
	private Connection cx;
	private String dbName;
	
	
	

	public EntityImpl() throws Exception
	{
		findCol = Outside.service(this,"gus06.jdbc.generic.perform.find.colinfolist.db");
		findFk = Outside.service(this,"gus06.jdbc.mysql.perform.fk.full.db");
		findUk = Outside.service(this,"gus06.jdbc.mysql.perform.uk.full.db");
		count = Outside.service(this,"gus06.jdbc.mysql.perform.select.count");
		countFkOrphans = Outside.service(this,"gus06.jdbc.mysql.perform.select.count.fk0.orphans");
		threadManager = Outside.service(this,"gus.x.thread.wrapper1");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		buildDataTable = Outside.service(this,"gus06.jdbc.gui.analyze1.builddata.table");
		buildDataColumn = Outside.service(this,"gus06.jdbc.gui.analyze1.builddata.column");
		
		gui1 = Outside.service(this,"*gus06.jdbc.gui.analyze1.gui1");
		gui2 = Outside.service(this,"*gus06.jdbc.gui.analyze1.gui2");
		gui3 = Outside.service(this,"*gus06.jdbc.gui.analyze1.gui3");
		
		tab.v("Tables",gui1.i());
		tab.v("Columns",gui2.i());
		tab.v("Graph",gui3.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(t!=null && t.isAlive()) return;
		cx = (Connection) o[0];
		dbName = (String) o[1];
		
		t = (Thread) threadManager.t(this);
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			Map mapTables = new HashMap();
			Map mapColumns = new HashMap();
			Map mapTablesPK = new HashMap();
			
			List listCol = (List) findCol.t(new Object[]{cx, dbName});
			for(int i=0;i<listCol.size();i++)
			{
				Map map = (Map) listCol.get(i);
				
				String tableName = (String) map.get(COL1_TABLE);
				String colName = (String) map.get(COL1_COLUMN_NAME);
				String colKey = (String) map.get(COL1_COLUMN_KEY);
				
				if(colKey.equals("PRI"))
				{
					if(!mapTablesPK.containsKey(tableName))
						mapTablesPK.put(tableName, new ArrayList());
					((List)mapTablesPK.get(tableName)).add(colName);
				}
				
				if(!mapTables.containsKey(tableName))
					mapTables.put(tableName, buildDataTable.t(tableName));
				((P) mapTables.get(tableName)).p(map);
				
				if(!mapColumns.containsKey(colName))
					mapColumns.put(colName, buildDataColumn.t(colName));
				((P) mapColumns.get(colName)).p(map);
			}
			
			List listFk = (List) findFk.t(new Object[]{cx, dbName});
			for(int i=0;i<listFk.size();i++)
			{
				Map map = (Map) listFk.get(i);
				
				String tableName = (String) map.get(COL2_TABLE_NAME);
				String colName = (String) map.get(COL2_COLUMN_NAME);
				String refTableName = (String) map.get(COL2_REFERENCED_TABLE_NAME);
				String refColName = (String) map.get(COL2_REFERENCED_COLUMN_NAME);
				
				((V) mapTables.get(tableName)).v("addFk",
					new String[]{colName,refTableName,refColName});
				((V) mapTables.get(refTableName)).v("addRefFk",
					new String[]{refColName,tableName,colName});
			}
			
			List listUk = (List) findUk.t(new Object[]{cx, dbName});
			for(int i=0;i<listUk.size();i++)
			{
				Map map = (Map) listUk.get(i);
				
				String tableName = (String) map.get(COL3_TABLE_NAME);
				String colName = (String) map.get(COL3_COLUMN_NAME);
				String constraintName = (String) map.get(COL3_CONSTRAINT_NAME);
				
				((V) mapTables.get(tableName)).v("addUk",
					new String[]{colName,constraintName});
			}
			
			Iterator it = mapTables.keySet().iterator();
			while(it.hasNext())
			{
				String tableName = (String) it.next();
				String path = dbName+"."+tableName;
				Integer nbRow = (Integer) count.t(new Object[]{cx, path});
				Object holder = mapTables.get(tableName);
				
				((V) holder).v("nbRow",nbRow);
				((V) holder).v("analyze",mapTablesPK);
				
				Map mapFk0 = (Map) ((R) holder).r("mapFk0");
				Iterator it1 = mapFk0.keySet().iterator();
				while(it1.hasNext())
				{
					String colName = (String) it1.next();
					String[] fk0Infos = (String[]) mapFk0.get(colName);
					
					Integer orphanCount = (Integer) countFkOrphans.t(new Object[]{cx,dbName,tableName,colName,fk0Infos[0],fk0Infos[1]});
					if(orphanCount>0) ((V) holder).v("setFk0e", new Object[]{colName, orphanCount});
				}
			}
			
			gui1.p(new Object[]{cx, dbName, mapTables});
			gui2.p(new Object[]{cx, dbName, mapColumns});
			gui3.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
}