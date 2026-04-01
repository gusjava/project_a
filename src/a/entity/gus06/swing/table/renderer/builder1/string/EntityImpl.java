package a.entity.gus06.swing.table.renderer.builder1.string;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}

	
	public Object t(Object obj) throws Exception
	{return new TableCellRenderer1(""+obj);}
	
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1(String s){super(s);}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
			return this;
		}
	}
}
