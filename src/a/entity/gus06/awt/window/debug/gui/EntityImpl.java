package a.entity.gus06.awt.window.debug.gui;

import a.framework.*;
import java.awt.Window;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import java.awt.Rectangle;

public class EntityImpl implements Entity, I, MouseListener {

	public String creationDate() {return "20190703";}


	private Service continuousRepaint;
	private Service iconProvider;
	private Service tooltip;
	

	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	private JLabel label;
	
	private TableModel0 model;



	public EntityImpl() throws Exception
	{
		continuousRepaint = Outside.service(this,"gus06.swing.comp.cust.continuousrepaint");
		iconProvider = Outside.service(this,"gus06.icon.provider");
		tooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		
		model = new TableModel0();
		
		table = new JTable(model);
		table.setShowGrid(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(Object.class,new TableCellRenderer1());
		table.addMouseListener(this);
		
		tooltip.p(table);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		continuousRepaint.p(table);
		resizeColumns(1,150);
		resizeColumns(2,150);
		resizeColumns(3,60);
		resizeColumns(4,60);
		resizeColumns(5,60);
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}
	
	
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private Window[] getWindows()
	{return Window.getWindows();}
	
	
	private Window windowAt(int x)
	{
		Window[] ww = getWindows();
		return x<ww.length ? ww[x] : null;
	}
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getRowCount(){return getWindows().length;}
		public int getColumnCount(){return 6;}
    	
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{return Object.class;}
		
		
		public String getColumnName(int y)
		{
			if(y==0) return "Title";
			if(y==1) return "Position";
			if(y==2) return "Size";
			if(y==3) return "Dispose";
			if(y==4) return "Center";
			if(y==5) return "Show";
			
			return null;
		}
		
		public Object getValueAt(int x, int y)
		{
			Window w = windowAt(x);
			if(w==null) return null;
			
			if(y==0) return getTitle(w);
			if(y==1) return getPosition(w.getBounds());
			if(y==2) return getSize(w.getBounds());
			if(y==3) return "dispose";
			if(y==4) return "center";
			if(y==5) return w.isVisible() ? "hide" : "show";
			
			return null;
		}
	}
	
	
	
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Window w = windowAt(row);
			
			setText(""+value);
			setBackground(background(w));
			setForeground(foreground(isSelected));
			setHorizontalAlignment(JLabel.LEFT);
			
			return this;
		}
		
		private Color background(Window w)
		{
			if(w.isActive()) return Color.YELLOW;
			if(!w.isVisible()) return Color.LIGHT_GRAY;
			return Color.WHITE;
		}
		
		private Color foreground(boolean isSelected)
		{
			return isSelected ? Color.RED : Color.BLACK;
		}
	}
	
	
	
	
	
	
	
	
	private String getTitle(Window w)
	{
		if(w instanceof Dialog) return ((Dialog) w).getTitle();
		if(w instanceof Frame) return ((Frame) w).getTitle();
		return "";
	}
	
	
	private String getPosition(Rectangle r)
	{return r.getX()+" "+r.getY();}
	
	
	private String getSize(Rectangle r)
	{return r.getWidth()+" "+r.getHeight();}
	
	
	
	private Icon icon(String key)
	{
		try{return (Icon) iconProvider.r(key);}
		catch(Exception e){Outside.err(this,"icon(String)",e);}
		return null;
	}
	
	
	
	
	
	
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e)
	{
		final int x = table.getSelectedRow();
		int y = table.getSelectedColumn();
		
		if(y==3)
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {disposeWindow(x);}
			});
		}
		if(y==4)
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {centerWindow(x);}
			});
		}
		if(y==5)
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {shiftWindow(x);}
			});
		}
	}
	
	
	
	private void disposeWindow(int x)
	{
		Window w = windowAt(x);
		if(w!=null) w.dispose();
	}
	
	private void centerWindow(int x)
	{
		Window w = windowAt(x);
		if(w!=null) w.setLocationRelativeTo(null);
	}
	
	private void shiftWindow(int x)
	{
		Window w = windowAt(x);
		if(w!=null) w.setVisible(!w.isVisible());
	}
}
