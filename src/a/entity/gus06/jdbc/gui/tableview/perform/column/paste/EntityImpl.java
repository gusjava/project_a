package a.entity.gus06.jdbc.gui.tableview.perform.column.paste;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190503";}


	private Service update;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		update = Outside.service(this,"gus06.jdbc.gui.tableview.tool.column.update");
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		String newValue = (String) clipboard.g();
		
		return update.f(new Object[]{table,data,newValue});
	}
}
