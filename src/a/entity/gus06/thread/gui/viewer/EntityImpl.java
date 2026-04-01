package a.entity.gus06.thread.gui.viewer;

import a.framework.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.management.ManagementFactory;


public class EntityImpl implements Entity, I, Runnable {

	public String creationDate() {return "20140730";}


	public static final long LAPSE = 20;
	public static final String EMPTY_STACK = "empty stack";
	public static final String NOT_FOUND = "not found";
    
	public static final Color COLOR_NEW = Color.BLUE;
	public static final Color COLOR_BLOCKED = Color.RED;
	public static final Color COLOR_RUNNABLE = Color.GREEN.darker();
	public static final Color COLOR_TERMINATED = Color.LIGHT_GRAY;
	public static final Color COLOR_NULL = Color.MAGENTA.darker();
	public static final Color COLOR_DEFAULT = Color.BLACK;


	
	
	private Service handleThread;
	private Service sortTable;
	private Service findTimer;
	
	private JPanel panel;
    
	private TableModel0 model;
	private JTable table;
	private JLabel labelNumber;
    
	private Timer timer;
	private TimerTask task;
	private Thread[] threads;
	private String[] gus06ste;

	


	public EntityImpl() throws Exception
	{
		handleThread = Outside.service(this,"gus06.sys.thread1.showmonitor.gui1.frame");
		sortTable = Outside.service(this,"gus06.swing.table.cust.sort2");
		findTimer = Outside.service(this,"gus06.time.timer.unique");
	
		threads = new Thread[0];
		gus06ste = new String[100];
    	
		model = new TableModel0();
		table = new JTable(model);

		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(String.class,new TableCellRenderer0());
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{if(e.getClickCount()==2) handleSelectedThread();}
		});
    		sortTable.p(table);
    	
		labelNumber = new JLabel(" ");
    	
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(labelNumber,BorderLayout.SOUTH);
        
		initColumnSize(0,50);//id
		initColumnSize(1,100);//state
		initColumnSize(2,50);//priority
		initColumnSize(3,50);//group
		initColumnSize(4,60);//daemon
		initColumnSize(5,40);//deep
		initColumnSize(6,90);//cpu time

		task = new TimerTask(){public void run() {update();}};
		
		timer = (Timer) findTimer.g();
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	private void update()
	{SwingUtilities.invokeLater(this);}
    
    
    
    
    
    
	public void run()
	{
		int count = Thread.activeCount();
 		threads = new Thread[count];
		Thread.enumerate(threads);

		int index = table.getSelectedRow();
		model.fireTableDataChanged();
		if(index!=-1) table.getSelectionModel().setSelectionInterval(index, index);
    	
		labelNumber.setText(" Threads number: "+count);
	}

	
	
	private void handleSelectedThread()
	{
		try
		{
			Thread t = selected();
			if(t!=null) handleThread.p(t);
		}
		catch(Exception e)
		{Outside.err(this,"handleSelectedThread()",e);}
	}
	
	
	private Thread getThread(int index)
	{
    		if(threads==null || threads.length<=index || index<0) return null;
    		return threads[index];
    	}
    
    


	private String group(Thread t)
	{
		ThreadGroup g = t.getThreadGroup();
		return g==null?"":g.getName();
	}
	
	private String state(Thread t)
	{
		return t.getState().name();
	}
	
	private String priority(Thread t)
	{
		return ""+t.getPriority();
	}
	
	private String id(Thread t)
	{
		return ""+t.getId();
	}
	
	private String daemon(Thread t)
	{
		return ""+t.isDaemon();
	}
	
	private String cpuTime(Thread t)
	{
		long cpuTime = ManagementFactory.getThreadMXBean().getThreadCpuTime(t.getId());
		return cpuTime==-1 ? "" : ""+cpuTime;
	}
	
	private String deep(Thread t)
	{
		StackTraceElement[] ste = t.getStackTrace();
		return ste!=null ? ""+ste.length : "";
	}

	private String first_STE(StackTraceElement[] ste)
	{
    		if(ste.length==0) return EMPTY_STACK;
    		return toString(ste[0]);
	}
	
	private String gus06_STE(StackTraceElement[] ste)
	{
		if(ste.length==0) return EMPTY_STACK;
		for(int n = ste.length,i=0;i<n;i++)
		if(ste[i]!=null && ste[i].getClassName().startsWith("gus06."))
			return toString(ste[i]);
		return NOT_FOUND;
	}
	
	private String latest_gus06_STE(StackTraceElement[] ste, int index)
	{
		String s = gus06_STE(ste);
		if(s.equals(EMPTY_STACK) || s.equals(NOT_FOUND))
		return gus06ste[index]!=null?gus06ste[index]:s;
		
		gus06ste[index] = s;
		return s;
	}
	
	
	private String toString(StackTraceElement ste)
	{
		if(ste==null) return "null";
		return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";
	}


	private Thread selected()
	{
		int row = table.getSelectedRow();
		return getThread(row);
	}
	
	
	private Color findForeground(Thread t)
	{
		if(t==null) return COLOR_NULL;
		
		Thread.State state = t.getState();
		if(state==Thread.State.NEW) return COLOR_NEW;
		if(state==Thread.State.BLOCKED) return COLOR_BLOCKED;
		if(state==Thread.State.RUNNABLE) return COLOR_RUNNABLE;
		if(state==Thread.State.TERMINATED) return COLOR_TERMINATED;
		return COLOR_DEFAULT;
	}
	
	
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 10;}
		public int getRowCount() {return threads.length;}
		public Class getColumnClass(int y) {return String.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "id";
			if(y==1) return "state";
			if(y==2) return "priority";
			if(y==3) return "group";
			if(y==4) return "daemon";
			if(y==5) return "deep";
			if(y==6) return "CPU time";
			if(y==7) return "name";
			if(y==8) return "latest gus06 STE";
			if(y==9) return "first actual STE";
			
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			Thread t = getThread(x);
			if(t==null) return null;
			
			if(y==0) return id(t);
			if(y==1) return state(t);
			if(y==2) return priority(t);
			if(y==3) return group(t);
			if(y==4) return daemon(t);
			if(y==5) return deep(t);
			if(y==6) return cpuTime(t);
			if(y==7) return t.getName();
			
			StackTraceElement[] ste = t.getStackTrace();
			if(ste==null) return null;
			
			if(y==8) return latest_gus06_STE(ste,x);
			if(y==9) return first_STE(ste);
			
			return null;
		}
	}
    
    
    
    
    
    
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		private Font font_p;
		private Font font_b;
    	
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
    			setBackground(Color.WHITE);
    			font_b = getFont().deriveFont(Font.BOLD);
    			font_p = getFont().deriveFont(Font.PLAIN);
    		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			String text = (String) value;
			setText(text);
			
			Font font = isSelected?font_b:font_p;
			setFont(font);
			
			Thread t = getThread(row);
			boolean isR = t instanceof R;
			Color foreground = findForeground(t);
			
			setForeground(foreground);
			setBackground(isR ? new Color(237,237,237) : Color.WHITE);
			
			return this;
		}
	}
}