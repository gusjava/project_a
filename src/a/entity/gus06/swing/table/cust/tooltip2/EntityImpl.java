package a.entity.gus06.swing.table.cust.tooltip2;

import a.framework.*;
import javax.swing.JTable;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.table.JTableHeader;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150430";}

	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		new TooltipHandler(table);
	}
	
	
	private class TooltipHandler extends MouseMotionAdapter
	{
		private JTable table;
		public TooltipHandler(JTable table)
		{
			super();
			this.table = table;
			table.addMouseMotionListener(this);
		}
		
		public void mouseMoved(MouseEvent evt)
		{
			Point p = evt.getPoint();
				
			int x = table.rowAtPoint(p);
			int y = table.columnAtPoint(p);
		
			table.setToolTipText(tooltipTable(x,y));
				
			JTableHeader header = table.getTableHeader();
			if(header!=null) header.setToolTipText(tooltipHeader(x,y));
		}
		
		private String tooltipTable(int x, int y)
		{
			if(x==-1) return null;
			if(y==-1) return null;
			
			Class columnClass = table.getColumnClass(y);
			TableCellRenderer renderer = table.getDefaultRenderer(columnClass);
			if(renderer!=null) return tooltipTable(renderer,x,y);
			
			renderer = table.getDefaultRenderer(Object.class);
			if(renderer!=null) return tooltipTable(renderer,x,y);
			
			Object value = table.getValueAt(x,y);
			return toString(value);
		}
		
		
		private String tooltipTable(TableCellRenderer renderer, int x, int y)
		{
			Object value = table.getValueAt(x,y);
			Component comp = renderer.getTableCellRendererComponent(table, value, false, false, x, y);
			
			if(comp instanceof JLabel) return ((JLabel) comp).getText();
			if(comp instanceof JTextComponent) return ((JTextComponent) comp).getText();
			return toString(value);
		}
		
		
		
		private String tooltipHeader(int x, int y)
		{
			if(x!=-1) return null;
			if(y==-1) return null;
			String columnName = table.getColumnName(y);
			
			TableCellRenderer renderer = table.getColumnModel().getColumn(y).getCellRenderer();
			if(renderer==null) renderer = table.getTableHeader().getDefaultRenderer();
			if(renderer==null)
			{
				Component comp = renderer.getTableCellRendererComponent(table, columnName, false, false, 0, y);
				if(comp instanceof JLabel) return ((JLabel) comp).getText();
				if(comp instanceof JTextComponent) return ((JTextComponent) comp).getText();
			}
			return columnName;
		}
		
		
		
		private String toString(Object obj)
		{
			if(obj==null) return null;
			if(obj instanceof String) return (String) obj;
			if(obj instanceof Number) return ""+obj;
			if(obj instanceof Boolean) return ""+obj;
			
			return null;
		}
	}
}
