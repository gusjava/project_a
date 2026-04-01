package a.entity.gus06.jdbc.gui.tableview.tool.cell.sql.select;

import a.framework.*;
import javax.swing.JTable;
import java.sql.Connection;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221003";}


	private Service findPk;
	private Service findCx;
	private Service buildSqlWhere;

	public EntityImpl() throws Exception
	{
		findPk = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykeys");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		buildSqlWhere = Outside.service(this,"gus06.jdbc.mysql.sql.where");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		if(table==null || data==null) return false;
		
		Connection cx = (Connection) findCx.t(data[0]);
		if(cx==null) return false;
		
		String dbName = (String) data[1];
		String tableName = (String) data[2];
		
		String path = dbName+"."+tableName;
		Set pkeys = (Set) findPk.t(new Object[]{cx,path});
		if(pkeys.isEmpty()) throw new Exception("No primary key found for path: "+path);
		
		String where = buildSqlWhere(table,pkeys);
		if(where==null) return false;
		
		String columns = buildSqlColumns(table);
		if(columns==null) return false;
		
		return "SELECT "+columns+" FROM "+path+" WHERE "+where;
	}
	
	
	
	private String buildSqlColumns(JTable table)
	{
		int[] columns = table.getSelectedColumns();
		if(columns==null || columns.length==0) return null;
		
		StringBuffer b = new StringBuffer();
		int nb = columns.length;
		for(int i=0;i<nb;i++)
		{
			String col = table.getColumnName(columns[i]);
			b.append(col);
			if(i<nb-1) b.append(", ");
		}
		return b.toString();
	}
	
	
	
	
	private String buildSqlWhere(JTable table, Set pkeys) throws Exception
	{
		int[] rows = table.getSelectedRows();
    		if(rows==null || rows.length==0) return null;
    		
		Map pkeysM = columnIndexMapping(table,pkeys);
		
		Map whereMap = new HashMap();
		Iterator it = pkeys.iterator();
		while(it.hasNext())
		{
			String pkey = (String) it.next();
			Integer column = (Integer) pkeysM.get(pkey);
			Object value = table.getValueAt(rows[0],column);
			whereMap.put(pkey,value);
		}
		return (String) buildSqlWhere.t(whereMap);
	}
	
	
	private Map columnIndexMapping(JTable table, Set pkeys) throws Exception
	{
		Map m = new HashMap();
		Iterator it = pkeys.iterator();
		while(it.hasNext())
		{
			String pkey = (String) it.next();
			int index = columnIndex(table,pkey);
			if(index==-1) throw new Exception("Column name not found inside JTable: "+pkey);
			m.put(pkey,index);
		}
		return m;
	}
	
	
	private Integer columnIndex(JTable table, String pkey)
	{
		int nb = table.getColumnCount();
		for(int i=0;i<nb;i++)
		if(table.getColumnName(i).equals(pkey)) return i;
		return -1;
	}
}