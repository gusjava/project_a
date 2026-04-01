package a.entity.gus06.jdbc.gui.tableview.tool.cell.update;

import a.framework.*;
import javax.swing.JTable;
import java.sql.Connection;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190502";}


	private Service findPk;
	private Service findCx;
	private Service perform;

	public EntityImpl() throws Exception
	{
		findPk = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykeys");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.row.update");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		Object newValue = o[2];
		
		if(table==null || data==null) return false;
		
		int[] rows = table.getSelectedRows();
    		int[] columns = table.getSelectedColumns();

    		if(rows==null || rows.length==0) return false;
    		if(columns==null || columns.length==0) return false;
		
		Connection cx = (Connection) findCx.t(data[0]);
		String dbName = (String) data[1];
		String tableName = (String) data[2];
		
		if(cx==null) return false;
		
		
		String path = dbName+"."+tableName;
		Set pkeys = (Set) findPk.t(new Object[]{cx,path});
		
		if(pkeys.isEmpty()) throw new Exception("No primary key found for path: "+path);
		
		Map pkeysM = columnIndexMapping(table,pkeys);
		
		
		Map map1 = new HashMap();
		for(int i=0;i<columns.length;i++)
		{
			String col = table.getColumnName(columns[i]);
			map1.put(col,newValue);
		}
		
		for(int i=0;i<rows.length;i++)
		{
			Map map = new HashMap();
			map.putAll(map1);
			
			Iterator it = pkeys.iterator();
			while(it.hasNext())
			{
				String pkey = (String) it.next();
				Integer column = (Integer) pkeysM.get(pkey);
				Object value = table.getValueAt(rows[i],column);
				
				map.put(pkey,value);
			}
			
			perform.p(new Object[]{cx,path,map,pkeys});
		}
		return true;
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
