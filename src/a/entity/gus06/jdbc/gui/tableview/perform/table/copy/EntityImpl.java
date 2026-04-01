package a.entity.gus06.jdbc.gui.tableview.perform.table.copy;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191003";}

	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.table.handle.all.toclipboard");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		perform.p(table);
		return false;
	}
}
