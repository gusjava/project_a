package a.entity.gus06.appli.gusappmonitor.applitab.gui.log;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20190412";}


	private Service readFile;
	private Service stateToColor;


	private JPanel panel;
	private JButton button;
	private JTable table;
	
	private List rows;
	private TableModel0 model;
	
	
	private Object config;
	

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.array.cs.utf8");
		stateToColor = Outside.service(this,"gus06.appli.gusappmonitor.tool.statetocolor");
		
		button = new JButton("Refresh");
		button.addActionListener(this);
		
		rows = new ArrayList();
		model = new TableModel0();
		table = new JTable(model);
		table.setDefaultRenderer(String.class,new TableCellRenderer0());
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{config = obj;}


	public void actionPerformed(ActionEvent e)
	{new Thread(this,"THREAD_"+getClass().getName()).start();}
	
	
	private String[] rowAt(int x)
	{
		if(rows.size()<=x) return null;
		return (String[]) rows.get(rows.size()-x-1);
	}
	
	
	public void run()
	{
		try
		{
			final List rows_ = buildRows();
			
			SwingUtilities.invokeLater(new Runnable(){
				public void run()
				{
					rows = rows_;
					model.fireTableDataChanged();
				}
			});
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	private Color stateToColor(String state)
	{
		try{return (Color) stateToColor.t(state);}
		catch(Exception e){Outside.err(this,"stateToColor(String)",e);}
		return Color.BLACK;
	}
	
	
	
	private List buildRows() throws Exception
	{
		List l = new ArrayList();
		if(config==null) return l;
		
		File logDir = (File) ((R) config).r("logDir");
		if(logDir==null) return l;
		
		File[] ff = logDir.listFiles();
		if(ff==null) return l;
		
		for(File f:ff)
		{
			String[] lines = (String[]) readFile.t(f);
			for(String line:lines)
			{
				String[] nn = line.split("[\t:]");
				if(nn.length!=3) throw new Exception("Invalid row: "+line);
				l.add(nn);
			}
		}
		return l;
	}
	


	private class TableModel0 extends AbstractTableModel
	{
		public int getRowCount(){return rows.size();}
		public int getColumnCount(){return 3;}
    	
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{return String.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Time";
			if(y==1) return "Appli";
			return "State";
		}
		
		public Object getValueAt(int x, int y)
		{
			String[] row = rowAt(x);
			return row!=null ? row[y] : "###";
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

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int x, int y)
		{
			String text = (String) value;
			String[] row = rowAt(x);
			
			Font font = isSelected?font_b:font_p;
			Color fg = findForeground(row);
			
			setText(text);
			setFont(font);
			setForeground(fg);
			return this;
		}
		
		private Color findForeground(String[] row)
		{
			if(row==null) return Color.BLACK;
			return stateToColor(row[2]);
		}
	}
}
