package a.entity.gus06.sys.runtask1.gui1;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Color;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import javax.swing.Icon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class EntityImpl implements Entity, ActionListener, MouseListener, I {

	public String creationDate() {return "20150604";}
	
	public static final Color SELECTED = new Color(255,255,204);
	
	public static final String ICONID_TIME = "time";
	public static final String ICONID_CANCEL = "ACTION_cancel";
	
	public static final String STATE_PENDING = "pending";
	public static final String STATE_RUNNING = "running";


	private Service manager;
	private Service continuousRepaint;
	private Service iconProvider;
	private Service tooltip;
	
	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	private JLabel label;
	
	private List queue;
	private int[] numbers;
	private TableModel0 model;
	


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.runtask1.manager");
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
		resizeColumns(2,100);
		resizeColumns(4,120);
		resizeColumns(5,120);
		resizeColumns(6,50);
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		manager.addActionListener(this);
		updateGui();
	}
	
	
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			queue = (List) manager.r("queue");
			numbers = (int[]) manager.r("numbers");
			
			label.setText(labelDisplay());
			SwingUtilities.invokeLater(model);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	private String labelDisplay()
	{
		int total = queue.size();
		int pending = numbers[0];
		int running = numbers[1];
		
		return " Number: "+total+"     Pending: "+pending+"     Running: "+running;
	}
	
	
	
	
	private Object r(R r, String key)
	{
		try{return r.r(key);}
		catch(Exception e)
		{
			Outside.err(this,"r(R,String)",e);
			return e;
		}
	}
	
	
	private Icon icon(String key)
	{
		try{return (Icon) iconProvider.r(key);}
		catch(Exception e){Outside.err(this,"icon(String)",e);}
		return null;
	}


	private Icon iconState(String state)
	{return icon("TASK_"+state);}
	
	
	
	private Icon iconCancel(String state)
	{
		if(state.equals(STATE_PENDING) || state.equals(STATE_RUNNING))
			return icon(ICONID_CANCEL);
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
		
		if(y==6)
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {cancelTask(x);}
			});
		}
	}
	
	
	private void cancelTask(int x)
	{
		try
		{
			E holder = (E) model.holderAt(x);
			holder.e();
		}
		catch(Exception e)
		{Outside.err(this,"cancelTask(int)",e);}
	}






	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		private volatile List list;
        
		public TableModel0()
		{list = new ArrayList();}
    	
		public int getRowCount(){return list.size();}
		public int getColumnCount(){return 7;}
    	
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{return Object.class;}
		
		public Object holderAt(int x)
		{return list.get(list.size()-1-x);}
		
		
		public String getColumnName(int y)
		{
			if(y==0) return "Target";
			if(y==1) return "Task";
			if(y==2) return "State";
			if(y==3) return "Progression";
			if(y==4) return "Time left";
			if(y==5) return "Ending time";
			if(y==6) return "Cancel";
			return null;
		}
		
    	
		public Object getValueAt(int x, int y)
		{
			R holder = (R) holderAt(x);
			
			if(y==0) return r(holder,"path");
			if(y==1) return r(holder,"task");
			if(y==2) return r(holder,"state");
			if(y==3) return r(holder,"progresscomp");
			if(y==4) return r(holder,"time_left");
			if(y==5) return r(holder,"time_end");
			if(y==6) return r(holder,"state");
			return null;
		}
		
		public void run()
		{
			synchronized(queue)
			{
				list.clear();
				list.addAll(queue);
			}
			fireTableDataChanged();
		}
	}
	
	
	
	
	
	
	
	
	
	
	private class TableCellRenderer1 implements TableCellRenderer
	{
		private JLabel label;
		
		public TableCellRenderer1()
		{
			label = new JLabel();
			label.setOpaque(true);
			label.setBackground(Color.WHITE);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int raw, int column)
		{
			try
			{
				if(value==null) return null_();
				if(value instanceof Exception) return err(value);
			
				if(column==0) return column0(value,isSelected);
				if(column==1) return column1(value,isSelected);
				if(column==2) return column2(value,isSelected);
				if(column==3) return column3(value,isSelected);
				if(column==4) return column4(value,isSelected);
				if(column==5) return column5(value,isSelected);
				if(column==6) return column6(value,isSelected);
				return null;
			}
			catch(Throwable t)
			{
				label.setText(t.toString());
				return label;
			}
		}
		
		
		private Component null_()
		{
			label.setText("NULL");
			label.setIcon(null);
			return label;
		}
		
		
		private Component err(Object value)
		{
			Exception e = (Exception) value;
			label.setText(e.getMessage());
			label.setIcon(null);
			return label;
		}
		
		
		private Component column0(Object value, boolean isSelected)
		{
			File f = new File((String) value);
			label.setText(f.getName());
			label.setIcon(findIcon(f));
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Component column1(Object value, boolean isSelected)
		{
			Class c = value.getClass();
			label.setText(findTaskName(c.getName()));
			label.setIcon(null);
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Component column2(Object value, boolean isSelected)
		{
			String state = (String) value;
			label.setText(state);
			label.setIcon(iconState(state));
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Component column3(Object value, boolean isSelected)
		{
			Component comp = (Component) value;
			comp.setBackground(color(isSelected));
			return comp;
		}
		
		private Component column4(Object value, boolean isSelected)
		{
			String t = (String) value;
			label.setText(t);
			label.setIcon(icon(ICONID_TIME));
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Component column5(Object value, boolean isSelected)
		{
			String t = (String) value;
			label.setText(t);
			label.setIcon(icon(ICONID_TIME));
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Component column6(Object value, boolean isSelected)
		{
			String state = (String) value;
			label.setText("");
			label.setIcon(iconCancel(state));
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.CENTER);
			return label;
		}
		
		
		
		private Icon findIcon(File f)
		{
			if(f==null || !f.exists()) return null;
			return FileSystemView.getFileSystemView().getSystemIcon(f);
		}
		
		
		private String findTaskName(String s)
		{
			s = s.replace(".EntityImpl","");
			s = s.split("runtask\\.")[1];
			return s.replace(".","_");
		}
		
		
		private Color color(boolean isSelected)
		{return isSelected?SELECTED:Color.WHITE;}
	}
}
