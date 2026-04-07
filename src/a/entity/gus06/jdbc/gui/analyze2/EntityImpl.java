package a.entity.gus06.jdbc.gui.analyze2;

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

	public String creationDate() {return "20230226";}
	
	public static final String COL2_TABLE_NAME = "TABLE_NAME";
	public static final String COL2_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL2_REFERENCED_TABLE_NAME = "REFERENCED_TABLE_NAME";
	public static final String COL2_REFERENCED_COLUMN_NAME = "REFERENCED_COLUMN_NAME";
	
	public static final String COL3_TABLE_NAME = "TABLE_NAME";
	public static final String COL3_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL3_CONSTRAINT_NAME = "CONSTRAINT_NAME";


	private Service findTablesPK;
	private Service findCol;
	private Service findFk;
	private Service findUk;
	private Service count;
	private Service tab;
	private Service threadManager;
	private Service buildDataTable;
	private Service gui1;
	private Service gui2;
	private Service countFkOrphans;
	
	private Thread t;
	private Connection cx;
	private String dbName;
	private String tableName;
	
	
	

	public EntityImpl() throws Exception
	{
		findTablesPK = Outside.service(this,"gus06.jdbc.mysql.perform.pk.map2.db");
		findCol = Outside.service(this,"gus06.jdbc.generic.perform.find.colinfolist.table");
		findFk = Outside.service(this,"gus06.jdbc.mysql.perform.fk.full.table2");
		findUk = Outside.service(this,"gus06.jdbc.mysql.perform.uk.full.table");
		count = Outside.service(this,"gus06.jdbc.mysql.perform.select.count");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		threadManager = Outside.service(this,"gus.x.thread.wrap1");
		buildDataTable = Outside.service(this,"gus06.jdbc.gui.analyze1.builddata.table");
		gui1 = Outside.service(this,"*gus06.jdbc.gui.analyze2.gui1");
		gui2 = Outside.service(this,"*gus06.jdbc.gui.analyze2.gui2");
		countFkOrphans = Outside.service(this,"gus06.jdbc.mysql.perform.select.count.fk0.orphans");
		
		tab.v("Columns",gui1.i());
		tab.v("Data",gui2.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		if(t!=null && t.isAlive()) return;
		cx = (Connection) o[0];
		dbName = (String) o[1];
		tableName = (String) o[2];
		
		t = (Thread) threadManager.t(this);
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			Object holder = buildDataTable.t(tableName);
			
			List listCol = (List) findCol.t(new Object[]{cx, dbName, tableName});
			for(int i=0;i<listCol.size();i++)
			{
				Map map = (Map) listCol.get(i);
				((P) holder).p(map);
			}
			
			List listFk = (List) findFk.t(new Object[]{cx, dbName, tableName});
			for(int i=0;i<listFk.size();i++)
			{
				Map map = (Map) listFk.get(i);
				
				String tableName_ = (String) map.get(COL2_TABLE_NAME);
				String colName_ = (String) map.get(COL2_COLUMN_NAME);
				String refTableName_ = (String) map.get(COL2_REFERENCED_TABLE_NAME);
				String refColName_ = (String) map.get(COL2_REFERENCED_COLUMN_NAME);
				
				if(tableName.equals(tableName_))
				((V) holder).v("addFk", new String[]{colName_,refTableName_,refColName_});
				
				if(tableName.equals(refTableName_))
				((V) holder).v("addRefFk", new String[]{refColName_,tableName_,colName_});
			}
			
			List listUk = (List) findUk.t(new Object[]{cx, dbName, tableName});
			for(int i=0;i<listUk.size();i++)
			{
				Map map = (Map) listUk.get(i);
				
				String colName_ = (String) map.get(COL3_COLUMN_NAME);
				String constraintName_ = (String) map.get(COL3_CONSTRAINT_NAME);
				
				((V) holder).v("addUk", new String[]{colName_,constraintName_});
			}
			
			Map mapTablesPK = (Map) findTablesPK.t(new Object[]{cx, dbName});
			((V) holder).v("analyze",mapTablesPK);
			
			Map mapFk0 = (Map) ((R) holder).r("mapFk0");
			Iterator it = mapFk0.keySet().iterator();
			while(it.hasNext())
			{
				String colName = (String) it.next();
				String[] fk0Infos = (String[]) mapFk0.get(colName);
				
				Integer orphanCount = (Integer) countFkOrphans.t(new Object[]{cx,dbName,tableName,colName,fk0Infos[0],fk0Infos[1]});
				if(orphanCount>0) ((V) holder).v("setFk0e", new Object[]{colName, orphanCount});
			}
			
			Integer nbRow = (Integer) count.t(new Object[]{cx, dbName+"."+tableName});
			((V) holder).v("nbRow",nbRow);
			
			gui1.p(new Object[]{cx, dbName, tableName, holder});
			gui2.p(new Object[]{cx, dbName, tableName, holder});
			
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
}