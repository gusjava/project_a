package a.entity.gus06.jdbc.gui.tableview.perform.table.truncate;

import a.framework.*;
import javax.swing.JTable;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190517";}


	private Service perform;
	private Service findCx;
	private Service confirm;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.generic.perform.table.truncate.force");
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		if(table==null || data==null) return false;
		
		Connection cx = (Connection) findCx.t(data[0]);
		String dbName = (String) data[1];
		String tableName = (String) data[2];
		
		if(cx==null) return false;
		
		String path = dbName+"."+tableName;
		
		String message = "You are about to truncate table\n: "+path+"\nDo you wish to continue ?";
		if(!confirm.f(message)) return false;
		
		perform.p(new Object[]{cx,path});
		
		return true;
	}
}
