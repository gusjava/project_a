package a.entity.gus06.swing.table.cust.tooltip.color;

import a.framework.*;
import javax.swing.JTable;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.table.JTableHeader;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250228";}

	
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
		
			table.setToolTipText(tooltip1(x,y));
			table.repaint();
				
			JTableHeader header = table.getTableHeader();
			if(header!=null) header.setToolTipText(tooltip2(x,y));
		}
		
		private String tooltip1(int x, int y)
		{
			if(x==-1) return null;
			if(y==-1) return null;
			Color c = (Color) table.getValueAt(x,y);
			return toString(c);
		}
		
		private String tooltip2(int x, int y)
		{
			if(x!=-1) return null;
			if(y==-1) return null;
			return table.getColumnName(y);
		}
		
		private String toString(Color c)
		{
			if(c==null) return null;
			int r = c.getRed();
			int g = c.getGreen();
			int b = c.getBlue();
			int a = c.getAlpha();
			
			return "RGBa=["+r+"-"+g+"-"+b+"-"+a+"]";
		}
	}
}