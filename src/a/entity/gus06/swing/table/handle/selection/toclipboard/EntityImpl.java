package a.entity.gus06.swing.table.handle.selection.toclipboard;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190502";}
	
	private Service clipboard;
	private Service selectionToString;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		selectionToString = Outside.service(this,"gus06.swing.table.selection.tostring");
	}

	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		String s = (String) selectionToString.t(table);
		clipboard.p(s);
	}
}
