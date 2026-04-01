package a.entity.gus06.swing.table.cust.sort1;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150430";}
	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		TableSorter sorter = new TableSorter(table.getModel());
		sorter.setTableHeader(table.getTableHeader());
		table.setModel(sorter);
	}
}
