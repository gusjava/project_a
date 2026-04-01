package a.entity.gus06.sys.script1.manager.gui1;

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
import java.util.Date;

public class EntityImpl implements Entity, ActionListener, MouseListener, I {

	public String creationDate() {return "20180117";}
	
	public static final Color SELECTED = new Color(255,255,204);

	public static final String ICONID_TIME = "time";
	public static final String ICONID_STOP = "ACTION_cancel";
	public static final String ICONID_SCRIPT = "FILE_gus";
	

	private Service manager;
	private Service continuousRepaint;
	private Service iconProvider;
	private Service formatDate;
	private Service duration;
	private Service tooltip;
	
	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	private JLabel label;
	
	private List list;
	private TableModel0 model;
	


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.script1.manager");
		continuousRepaint = Outside.service(this,"gus06.swing.comp.cust.continuousrepaint");
		iconProvider = Outside.service(this,"gus06.icon.provider");
		formatDate = Outside.service(this,"gus06.time.date.format.datetime.en.format1");
		duration = Outside.service(this,"gus06.time.duration.tonow.s.fr");
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
		resizeColumns(1,130);
		resizeColumns(2,80);
		resizeColumns(3,40);
		
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
			list = (List) manager.r("list");
			
			label.setText(labelDisplay());
			SwingUtilities.invokeLater(model);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	private String labelDisplay()
	{
		int total = list.size();
		return " Number: "+total;
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
	
	
	private String formatDate(Date d)
	{
		try{return (String) formatDate.t(d);}
		catch(Exception e){Outside.err(this,"formatDate(Date)",e);}
		return null;
	}
	
	
	private String duration(Date d)
	{
		try{return (String) duration.t(d);}
		catch(Exception e){Outside.err(this,"duration(Date)",e);}
		return null;
	}
	
	
	private String truncate(String script)
	{
		String s = script.replace("\n"," ").replace("\t"," ");
		if(s.length()<=50) return s;
		return s.substring(50)+" ...";
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
				public void run() {stopScript(x);}
			});
		}
	}
	
	
	private void stopScript(int x)
	{
		try
		{
			P holder = (P) model.holderAt(x);
			holder.p("stop");
		}
		catch(Exception e)
		{Outside.err(this,"stopScript(int)",e);}
	}






	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		private volatile List list1;
        
		public TableModel0()
		{list1 = new ArrayList();}
    	
		public int getRowCount(){return list1.size();}
		public int getColumnCount(){return 4;}
    	
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{return Object.class;}
		
		public Object holderAt(int x)
		{return list1.get(list1.size()-1-x);}
		
		
		public String getColumnName(int y)
		{
			if(y==0) return "Source";
			if(y==1) return "Start";
			if(y==2) return "Duration";
			if(y==3) return "Stop";
			return null;
		}
		
		public Object getValueAt(int x, int y)
		{
			R holder = (R) holderAt(x);
			
			if(y==0) return r(holder,"src");
			if(y==1) return r(holder,"startDate");
			if(y==2) return r(holder,"startDate");
			if(y==3) return "Stop";
			return null;
		}
		
		public void run()
		{
			synchronized(list)
			{
				list1.clear();
				list1.addAll(list);
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
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			try
			{
				if(value==null) return null_();
			
				if(column==0) return column0(value,isSelected);
				if(column==1) return column1(value,isSelected);
				if(column==2) return column2(value,isSelected);
				if(column==3) return column3(value,isSelected);
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
		
		
		
		private Component column0(Object value, boolean isSelected)
		{
			String display = value instanceof File ? ((File) value).getAbsolutePath() : truncate((String) value);
			
			label.setText(display);
			label.setIcon(icon(ICONID_SCRIPT));
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Component column1(Object value, boolean isSelected)
		{
			Date d = (Date) value;
			String display = formatDate(d);
			
			label.setText(display);
			label.setIcon(null);
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Component column2(Object value, boolean isSelected)
		{
			Date d = (Date) value;
			String display = duration(d);
			
			label.setText(display);
			label.setIcon(null);
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Component column3(Object value, boolean isSelected)
		{
			label.setText("");
			label.setIcon(icon(ICONID_STOP));
			label.setBackground(color(isSelected));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		private Color color(boolean isSelected)
		{return isSelected?SELECTED:Color.WHITE;}
	}
}
