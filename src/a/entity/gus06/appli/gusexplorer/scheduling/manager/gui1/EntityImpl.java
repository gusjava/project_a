package a.entity.gus06.appli.gusexplorer.scheduling.manager.gui1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import javax.swing.filechooser.FileSystemView;
import javax.swing.Icon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;

public class EntityImpl implements Entity, ActionListener, MouseListener, I {

	public String creationDate() {return "20180119";}
	
	public static final Color COLOR_SELECTED = new Color(255,255,204);
	public static final Color COLOR_DISABLED = Color.LIGHT_GRAY;

	public static final String ICONID_TIME = "time";
	public static final String ICONID_SCRIPT = "FILE_gus";
	public static final String ICONID_CLOCK = "UTIL_clock";
	
	public static final String KEY_DISABLED = "disabled";
	public static final String KEY_PROP_FILE = "propFile";
	public static final String KEY_SCRIPT_FILE = "scriptFile";
	public static final String KEY_CURRENT_DATE = "current_date";
	public static final String KEY_LAST_DATE = "last_date";
	

	private Service manager;
	private Service continuousRepaint;
	private Service formatDate;
	private Service findDate;
	private Service iconProvider;
	private Service duration;
	private Service tooltip;
	private Service buildDisplay;
	private Service getName0;
	
	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	private JLabel label;
	
	private List list;
	private TableModel0 model;
	


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.scheduling.manager");
		continuousRepaint = Outside.service(this,"gus06.swing.comp.cust.continuousrepaint");
		formatDate = Outside.service(this,"gus06.time.date.format.datetime.en.format1");
		findDate = Outside.service(this,"gus06.find.date");
		iconProvider = Outside.service(this,"gus06.icon.provider");
		duration = Outside.service(this,"gus06.time.duration.tonow.s.fr");
		tooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		buildDisplay = Outside.service(this,"gus06.sys.scheduling1.builddisplay");
		getName0 = Outside.service(this,"gus06.file.getname0");
		
		
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
		resizeColumns(0,100);
		resizeColumns(1,100);
		resizeColumns(2,140);
		
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
	
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private boolean boolDF(Map map, String key)
	{
		if(!map.containsKey(key)) return false;
		String v = (String) map.get(key);
		return Boolean.parseBoolean(v);
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
	
	private Date findDate(Object obj)
	{
		try{return (Date) findDate.t(obj);}
		catch(Exception e){Outside.err(this,"findDate(Object)",e);}
		return null;
	}
	
	private String duration(Date d)
	{
		try{return (String) duration.t(d);}
		catch(Exception e){Outside.err(this,"duration(Date)",e);}
		return null;
	}
	
	private String ruleDisplay(Object obj)
	{
		try{return (String) buildDisplay.t(obj);}
		catch(Exception e){Outside.err(this,"ruleDisplay(Object)",e);}
		return null;
	}
	
	private String fileName(Object obj)
	{
		try{return (String) getName0.t(obj);}
		catch(Exception e){Outside.err(this,"fileName(Object)",e);}
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
	public void mousePressed(MouseEvent e) {}
	
	



	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		private volatile List list1;
        
		public TableModel0()
		{list1 = new ArrayList();}
    	
		public int getRowCount(){return list1.size();}
		public int getColumnCount(){return 5;}
    	
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{return Object.class;}
		
		public Map mapAt(int x)
		{return (Map) list1.get(list1.size()-1-x);}
		
		
		public String getColumnName(int y)
		{
			if(y==0) return "Name";
			if(y==1) return "State";
			if(y==2) return "Last";
			if(y==3) return "Rule";
			if(y==4) return "Script";
			return null;
		}
		
		public Object getValueAt(int x, int y)
		{
			Map map = mapAt(x);
			
			if(y==0) return buildCol0(map);
			if(y==1) return buildCol1(map);
			if(y==2) return buildCol2(map);
			if(y==3) return buildCol3(map);
			if(y==4) return buildCol4(map);
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
	
	
	
	// COL0 : NAME
	private Object buildCol0(Map map)
	{return fileName(get(map,KEY_PROP_FILE));}
	
	// COL1 : STATE
	private Object buildCol1(Map map)
	{return findDate(get(map,KEY_CURRENT_DATE));}
	
	// COL2 : LAST
	private Object buildCol2(Map map)
	{return findDate(get(map,KEY_LAST_DATE));}
	
	// COL3 : RULE
	private Object buildCol3(Map map)
	{return ruleDisplay(map);}
	
	// COL4 : SCRIPT
	private Object buildCol4(Map map)
	{return get(map,KEY_SCRIPT_FILE);}
	
	
	
	
	
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
				Map map = model.mapAt(row);
				boolean isDisabled = boolDF(map,KEY_DISABLED);
				
				label.setBackground(backgroundColor(isSelected));
				label.setForeground(foregroundColor(isDisabled));
			
				if(column==0) return column0(value);
				if(column==1) return column1(value,isDisabled);
				if(column==2) return column2(value);
				if(column==3) return column3(value);
				if(column==4) return column4(value);

				label.setIcon(null);
				label.setText(value.toString());
				return label;
			}
			catch(Throwable t)
			{
				label.setText(t.toString());
				return label;
			}
		}
		
		// COL0 : NAME
		private Component column0(Object value)
		{
			label.setText((String) value);
			label.setIcon(icon(ICONID_CLOCK));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		// COL1 : STATE
		private Component column1(Object value, boolean isDisabled)
		{
			Date d = (Date) value;
			String display = d!=null ? duration(d) : (isDisabled ? "disabled" : "waiting");
			
			label.setText(display);
			label.setIcon(null);
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		// COL2 : LAST
		private Component column2(Object value)
		{
			Date d = (Date) value;
			String display = d!=null ? formatDate(d) : "";
			
			label.setText(display);
			label.setIcon(null);
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		// COL3 : RULE
		private Component column3(Object value)
		{
			String display = value!=null ? (String) value : "";
			
			label.setText(display);
			label.setIcon(null);
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		// COL4 : SCRIPT
		private Component column4(Object value)
		{
			String display = value!=null? ((File) value).getAbsolutePath() : "";
			
			label.setText(display);
			label.setIcon(icon(ICONID_SCRIPT));
			label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
		
		
		private Color backgroundColor(boolean isSelected)
		{return isSelected ? COLOR_SELECTED : Color.WHITE;}
		
		private Color foregroundColor(boolean isDisabled)
		{return isDisabled ? COLOR_DISABLED : Color.BLACK;}
	}
}
