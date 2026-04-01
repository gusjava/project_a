package a.entity.gus06.sys.mailclient1.gui.tab1.messages.table;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.mail.Folder;
import javax.mail.Address;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, I, P, G, V, ListSelectionListener, ActionListener {

	public String creationDate() {return "20201115";}
	
	public static final Color SELECTION_COLOR = new Color(210,235,235);


	private Service tableTooltip;
	private Service formatDate;
	private Service formatAddresses;
	private Service isFolderOut;
	private Service engine;
	
	private JPanel panel;
	private JLabel label;
	private JScrollPane scroll;
	private JTable table;
	private TableModel1 model;
	
	private Thread t;
	
	private Folder folder;
	private List list;
	
	private File root;
	private File folderDir;

	public EntityImpl() throws Exception
	{
		tableTooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		formatDate = Outside.service(this,"gus06.time.date.format.datetime.fr.format2");
		formatAddresses = Outside.service(this,"gus06.sys.mailclient1.tool.addressarray.format.html");
		isFolderOut = Outside.service(this,"gus06.sys.mailclient1.tool.folder.isout");
		engine = Outside.service(this,"*gus06.sys.mailclient1.gui.tab1.messages.engine");
		
		list = new ArrayList();
		model = new TableModel1();
		
		table = new JTable(model);
		table.setShowGrid(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		table.setDefaultRenderer(Object.class,new TableCellRenderer1());
		tableTooltip.p(table);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		label = new JLabel();
		label.setFont(label.getFont().deriveFont(Font.ITALIC));
		label.setForeground(Color.GRAY);
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		
		engine.addActionListener(this);
	}
	
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	public void actionPerformed(ActionEvent e)
	{newMessageRetrieved();}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{
		int row = table.getSelectedRow();
		return row>=0 ? list.get(row) : null;
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive())
		{
			engine.v("interrupt", null);
			t.join();
		}
		
		list.clear();
		
		folder = (Folder) obj;
		engine.v("folder", folder);
		
		model.fireTableStructureChanged();
		table.repaint();
		
		resizeColumns(0,20);
		resizeColumns(1,20);
		resizeColumns(2,120);
		
		t = new Thread(new Runnable() {
			public void run() {
				try {engine.e();}
				catch(Exception e)
				{e.printStackTrace();}
			}
		}, "THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("root")) {setRoot((File) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	private void setRoot(File root) throws Exception
	{
		this.root = root;
		engine.v("root",root);
	}
	
	
	
	
	private void newMessageRetrieved()
	{
		try
		{
			Object holder = engine.g();
			
			list.add(0,holder);
			model.fireTableRowsInserted(0,0);
			table.repaint();
			
			String progress = (String) engine.r("progress");
			label.setText(" Currently loading: "+progress+" ");
		}
		catch(Exception e)
		{Outside.err(this,"newMessageRetrieved()",e);}
	}
	
	
	
	
	private boolean isFolderOut()
	{
		try{return isFolderOut.f(folder);}
		catch(Exception e){Outside.err(this,"private boolean isFolderOut()",e);}
		return false;
	}
	
	
	
	
	private Object findValue(Object holder, int y)
	{
		try
		{
			boolean isOut = ((F) holder).f("out");
			
			if(y==0) return ((R) holder).r("icon");
			if(y==1) return ((R) holder).r("attachments");
			if(y==2) return ((R) holder).r(isOut ? "sentDate" : "receivedDate");
			if(y==3) return ((R) holder).r(isOut ? "recipientsTO" : "from");
			return ((R) holder).r("subject");
		}
		catch(Exception e){Outside.err(this,"findValue(Object,int)",e);}
		return "###";
	}
	
	private String findColumnName(int y)
	{
		try
		{
			if(y==0) return "";
			if(y==1) return "F";
			if(y==2) return "Date";
			if(y==3) return isFolderOut() ? "Sent to" : "Received from";
			return "Subject";
		}
		catch(Exception e){Outside.err(this,"findColumnName(int)",e);}
		return "###";
	}
	
	
	
	
	private class TableModel1 extends AbstractTableModel
	{
		public int getRowCount() {return list!=null?list.size():0;}
		public int getColumnCount() {return 5;}
		
		public Class getColumnClass(int y)
		{return Object.class;}
		
		public String getColumnName(int y)
		{return findColumnName(y);}

		public Object getValueAt(int x, int y)
		{return findValue(list.get(x),y);}
	}
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		private Font font_p;
		private Font font_b;
		
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
			font_p = getFont().deriveFont(Font.PLAIN);
			font_b = getFont().deriveFont(Font.BOLD);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Object holder = list.get(row);
			boolean unseen = filter((F)holder,"unseen");
			
			if(column==0)
			{
				Icon icon = (Icon) value;
				setText("");
				setIcon(icon);
			}
			else if(column==1)
			{
				File[] attachments = (File[]) value;
				int fileNb = attachments.length;
				setText(fileNb>0 ? ""+fileNb : "");
				setIcon(null);
			}
			else if(column==2)
			{
				Date date = (Date) value;
				setText(formatDate(date));
				setIcon(null);
			}
			else if(column==3)
			{
				String addr = (String) value;
				setText(addr);
				setIcon(null);
			}
			else
			{
				String subject = (String) value;
				setText(subject);
				setIcon(null);
			}
			
			setFont(unseen ? font_b : font_p);
			setBackground(isSelected ? SELECTION_COLOR : Color.WHITE);
			
			return this;
		}
	}
	
	
	
	private String formatDate(Date date)
	{
		try
		{
			return (String) formatDate.t(date);
		}
		catch(Exception e)
		{Outside.err(this,"formatDate(Date)",e);}
		return "###";
	}
	
	private String formatAddresses(Address[] addr)
	{
		try
		{
			return (String) formatAddresses.t(addr);
		}
		catch(Exception e)
		{Outside.err(this,"formatAddresses(Date)",e);}
		return "###";
	}
	
	
	private boolean filter(F f, Object obj)
	{
		try{return f.f(obj);}
		catch(Exception e){Outside.err(this,"filter(F,Object)",e);}
		return false;
	}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selected();}
	
	private void selected()
	{send(this,"selected()");}
}