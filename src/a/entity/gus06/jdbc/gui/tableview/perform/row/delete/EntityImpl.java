package a.entity.gus06.jdbc.gui.tableview.perform.row.delete;

import a.framework.*;
import javax.swing.JTable;
import java.sql.Connection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190517";}


	private Service findPk;
	private Service findCx;
	private Service perform;
	private Service confirm;

	public EntityImpl() throws Exception
	{
		findPk = Outside.service(this,"gus06.jdbc.mysql.perform.table.findprimarykeys");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.rows.delete.where");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		if(table==null || data==null) return false;
		
		int[] rows = table.getSelectedRows();
    		if(rows==null || rows.length==0) return false;
		
		Connection cx = (Connection) findCx.t(data[0]);
		String dbName = (String) data[1];
		String tableName = (String) data[2];
		
		if(cx==null) return false;
		
		String message = "You are about to delete rows\nDo you wish to continue ?";
		if(!confirm.f(message)) return false;
		
		String path = dbName+"."+tableName;
		Set pkeys = (Set) findPk.t(new Object[]{cx,path});
		
		if(pkeys.isEmpty()) throw new Exception("No primary key found for path: "+path);
		Map pkeysM = columnIndexMapping(table,pkeys);
		
		for(int i=0;i<rows.length;i++)
		{
			Map whereMap = new HashMap();
			
			Iterator it = pkeys.iterator();
			while(it.hasNext())
			{
				String pkey = (String) it.next();
				Integer column = (Integer) pkeysM.get(pkey);
				Object value = table.getValueAt(rows[i],column);
				
				whereMap.put(pkey,value);
			}
			perform.p(new Object[]{cx,path,whereMap});
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
