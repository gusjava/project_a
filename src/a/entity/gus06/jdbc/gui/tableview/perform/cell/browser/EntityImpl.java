package a.entity.gus06.jdbc.gui.tableview.perform.cell.browser;

import a.framework.*;
import javax.swing.JTable;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190516";}


	private Service perform;
	private Service findCx;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.jdbcbrowser.show");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		if(table==null || data==null) return false;
		
		int[] rows = table.getSelectedRows();
		int[] columns = table.getSelectedColumns();

		if(rows==null || rows.length!=1) return false;
		if(columns==null || columns.length!=1) return false;
		
		String col = table.getColumnName(columns[0]);
		Object value = table.getValueAt(rows[0],columns[0]);
		
		Connection cx = (Connection) findCx.t(data[0]);
		String dbName = (String) data[1];
		String tableName = (String) data[2];
		
		if(cx==null) return false;
		perform.p(new Object[]{cx,dbName,tableName,col,value});
		return false;
	}
}
