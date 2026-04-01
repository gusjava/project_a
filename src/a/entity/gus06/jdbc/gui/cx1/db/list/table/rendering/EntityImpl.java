package a.entity.gus06.jdbc.gui.cx1.db.list.table.rendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231118";}

	public static final Color COLOR_MYSQLDB = new Color(204,102,0);
	public static final Color COLOR_EMPTY = Color.RED;
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	
	
	private Icon icon;
	
	public EntityImpl() throws Exception
	{
		icon = (Icon) Outside.resource(this,"icon#BASE");
	}


	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		table.setDefaultRenderer(Object.class, new TableCellRenderer0());
	}

	
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer0()
		{
			setOpaque(true);
			setBackground(Color.WHITE);
			setFont(getFont().deriveFont(Font.PLAIN));
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int x, int y)
		{
			String dbName = (String) table.getValueAt(x,0);
			Long count = (Long) table.getValueAt(x,1);
			
			setForeground(foreground(dbName, count));
			setBackground(background(isSelected));
			
			if(y==0)
			{
				setIcon(icon);
				setText(""+value);
				setHorizontalAlignment(JLabel.LEFT);
			}
			else
			{
				setIcon(null);
				setText(value+" ");
				setHorizontalAlignment(JLabel.RIGHT);
			}
			return this;
		}
	}
	
	
	private Color foreground(String dbName, Long count)
	{
		if(isMysqlDb(dbName)) return COLOR_MYSQLDB;
		if(count==0) return COLOR_EMPTY;
		return Color.BLACK;
	}
	
	private Color background(boolean isSelected)
	{
		if(isSelected) 
			return SELECTION_COLOR;
		return Color.WHITE;
	}
	
	
	private boolean isMysqlDb(String dbName)
	{
		return dbName.equals("mysql")
		 || dbName.equals("information_schema")
		 || dbName.equals("performance_schema")
		 || dbName.equals("sys")
		;
	}
}