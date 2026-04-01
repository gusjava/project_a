package a.entity.gus06.jdbc.gui.tableview.perform.row.edit;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190503";}


	private Service update;
	private Service input;

	public EntityImpl() throws Exception
	{
		update = Outside.service(this,"gus06.jdbc.gui.tableview.tool.row.update");
		input = Outside.service(this,"gus06.input.textarea.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		String newValue = (String) input.t("Value editor");
		
		return update.f(new Object[]{table,data,newValue});
	}
}
