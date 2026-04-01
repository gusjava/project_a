package a.entity.gus06.swing.table.handle.refresh;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170412";}
	
	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		AbstractTableModel model = (AbstractTableModel) table.getModel();
		model.fireTableDataChanged();
	}
}
