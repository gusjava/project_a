package a.entity.gus06.swing.table.renderer.builder1.comp;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}

	
	public Object t(Object obj) throws Exception
	{return new TableCellRenderer1((Component) obj);}
	
	
	
	private class TableCellRenderer1 implements TableCellRenderer
	{
		private Component comp;
		public TableCellRenderer1(Component comp){this.comp = comp;}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{return comp;}
	}
}
