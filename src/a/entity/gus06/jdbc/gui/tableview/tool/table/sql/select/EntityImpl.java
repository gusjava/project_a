package a.entity.gus06.jdbc.gui.tableview.tool.table.sql.select;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221003";}


	public EntityImpl() throws Exception
	{
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		if(table==null || data==null) return false;
		
		String dbName = (String) data[1];
		String tableName = (String) data[2];
		
		String path = dbName+"."+tableName;
		return "SELECT * FROM "+path;
	}
}