package a.entity.gus06.jdbc.gui.tableview.perform.row.empty;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190503";}


	private Service update;

	public EntityImpl() throws Exception
	{
		update = Outside.service(this,"gus06.jdbc.gui.tableview.tool.row.update");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		String newValue = "";
		
		return update.f(new Object[]{table,data,newValue});
	}
}
