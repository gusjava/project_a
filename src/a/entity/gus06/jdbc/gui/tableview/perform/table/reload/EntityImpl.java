package a.entity.gus06.jdbc.gui.tableview.perform.table.reload;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190517";}

	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		return true;
	}
}
