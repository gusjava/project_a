package a.entity.gus06.appli.entityhistory.gui.timetable;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTable;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import java.awt.Rectangle;


public class EntityImpl implements Entity, I, P, MouseListener {

	public String creationDate() {return "20150501";}
	
	public static final Color monthColumnColor = new Color(240,240,240);
	public static final Color weekendColor = new Color(200,248,150);
	public static final Color todayColor = Color.ORANGE;


	private Service tableTooltip;

	private PlanningTableModel model;
	private JTable table;
	
	private int currentYear;
	private Map freq;




	public EntityImpl() throws Exception
	{
		tableTooltip = Outside.service(this,"gus.x.swing.table.cust.tooltip1");
		
		currentYear = PlanningTool.currentYear();
		model = new PlanningTableModel();
		table = new JTable(model);
		
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Object.class,new PlanningTableCellRenderer());
		tableTooltip.p(table);
		
		resizeColumns();
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().addMouseListener(this);
	}
	
	
	private void resizeColumns()
	{
		table.getColumnModel().getColumn(0).setMinWidth(70);
		table.getColumnModel().getColumn(0).setMaxWidth(70);
	}
	
	
	
	public Object i() throws Exception
	{return table;}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		freq = (Map) obj;
		model.fireTableStructureChanged();
		resizeColumns();
	}
	
	private void changeYear(int v)
	{
		currentYear+=v;
		model.fireTableStructureChanged();
		resizeColumns();
	}
	
	
	
	
	
	
	private class PlanningTableModel extends AbstractTableModel
	{
		public int getColumnCount()			{return 32;}
		public int getRowCount()			{return 12;}
		public boolean isCellEditable(int x, int y)	{return false;}
		public Class getColumnClass(int y)		{return String.class;}

		public String getColumnName(int y)
		{
			if(y==0) return "\u25c0  "+currentYear+"  \u25b6";
			return ""+y;
		}
		
		public Object getValueAt(int x, int y)
		{
			if(y==0) return PlanningTool.month(x);
			return valueOfDay(x+1,y);
		}
		
		private String valueOfDay(int x, int y)
		{
			if(freq==null) return "";
			
			String x_ = x<10?"0"+x:""+x;
			String y_ = y<10?"0"+y:""+y;
			String timeStamp = currentYear+x_+y_;
			
			if(freq.containsKey(timeStamp))
				return ""+freq.get(timeStamp);
			return "";
		}
	}
	
	
	
	
	
	
	


	
	private class PlanningTableCellRenderer extends JLabel implements TableCellRenderer
	{
		public PlanningTableCellRenderer()
		{
			setOpaque(true);
			setFont(getFont().deriveFont(Font.PLAIN));
			setHorizontalAlignment(JLabel.CENTER);
		}
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			String text = (String)value;
			setText(text);
			
			if(column==0)
			{
				setBackground(monthColumnColor);
				return this;
			}
			if(!PlanningTool.isValidDay(currentYear,row,column-1))
			{
				setBackground(Color.GRAY);
				return this;
			}
			if(PlanningTool.isToday(currentYear,row,column-1))
			{
				setBackground(todayColor);
				return this;
			}
			if(PlanningTool.isWeekend(currentYear,row,column-1))
			{
				setBackground(weekendColor);
				return this;
			}
			setBackground(Color.WHITE);
			return this;
		}
	}
	
	
	
	
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e)
	{
		Point p = e.getPoint();
		int x = table.rowAtPoint(p);
		int y = table.columnAtPoint(p);
		
		if(x==0 && y==0)
		{
			Rectangle r = table.getCellRect(0,0,false);
			double xc = r.getCenterX();
			double xp = p.getX();
			
			if(xp < xc) changeYear(-1);
			else changeYear(1);
		}
	}
}
