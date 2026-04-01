package a.entity.gus06.swing.table.handle.all.toclipboard;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191003";}
	
	private Service clipboard;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
		toString = Outside.service(this,"gus06.swing.table.all.tostring");
	}

	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		String s = (String) toString.t(table);
		clipboard.p(s);
	}
}
