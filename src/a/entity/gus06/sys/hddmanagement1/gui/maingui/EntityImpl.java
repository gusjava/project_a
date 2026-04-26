package a.entity.gus06.sys.hddmanagement1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;
import java.util.Map;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.Icon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JButton;
import java.util.Iterator;

public class EntityImpl implements Entity, ActionListener, I, P, V {

	public String creationDate() {return "20191031";}
	
	
	public static final int COL_NB = 10;
	
	public static final String KEY_NAME = "name";
	public static final String KEY_SERIAL = "serial";
	public static final String KEY_DIRS = "dirs";
	
	public static final String KEY_FREE = "free";
	public static final String KEY_PATH = "path";
	public static final String KEY_RATIO = "ratio";
	public static final String KEY_TIME = "time";
	public static final String KEY_TOTAL = "total";
	public static final String KEY_USABLE = "usable";
	public static final String KEY_USED = "used";
	
	public static final Color COLOR_RATIO = new Color(51,153,255);
	public static final Color COLOR_SELECTED = Color.LIGHT_GRAY;
	public static final Color COLOR_LATE = Color.ORANGE;
	
	public static final String STATE_ONLINE = "ONLINE";
	public static final String STATE_OFFLINE = "OFFLINE";
	
	private Service findListing;
	private Service readProp;
	private Service tooltip;
	private Service formatter;
	private Service rootWatcher;
	private Service findHddMap;
	private Service performScan;
	private Service percent;

	private JPanel panel;
	private JTable table;
	private JLabel label;
	private JButton button;
	
	private TableModel1 model;
	private File root;
	private List list;
	
	private long totalSpace;
	private long freeSpace;
	
	private Icon iconOnline;
	private Icon iconOffline;
	
	private F filterName;
	private F filterLate;
	
	private Color colorRatio = COLOR_RATIO;
	private Color colorLate = COLOR_LATE;
	private Map descMap;
	private Map widthMap;
	
	private Map hddMap;

	public EntityImpl() throws Exception
	{
		findListing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		tooltip = Outside.service(this,"gus06.swing.table.cust.tooltip1");
		formatter = Outside.service(this,"gus06.file.size.formatter1.en");
		rootWatcher = Outside.service(this,"gus06.watching.dir.roots");
		findHddMap = Outside.service(this,"gus06.file.roots.map.name_serial");
		performScan = Outside.service(this,"gus06.sys.hddmanagement1.perform.scan");
		percent = Outside.service(this,"gus06.string.transform.format.number.percent.decimal1");
		
		iconOnline = (Icon) Outside.resource(this,"icon#UTIL_disk_online");
		iconOffline = (Icon) Outside.resource(this,"icon#UTIL_disk_offline");
		
		model = new TableModel1();
		table = new JTable(model);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.setShowGrid(false);
		
		table.setDefaultRenderer(String.class, new TableCellRendererString());
		table.setDefaultRenderer(Long.class, new TableCellRendererLong());
		table.setDefaultRenderer(Double.class, new TableCellRendererRatio());
		
		tooltip.p(table);
		
		resizeColumns(0,35);
		resizeColumns(1,70);
		resizeColumns(3,50);
		resizeColumns(4,50);
		resizeColumns(6,110);
		resizeColumns(7,40);
		
		label = new JLabel(" ");
		
		button = new JButton("Scan");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{scan();}
		});
		
		JPanel panelBottom = new JPanel(new BorderLayout());
		panelBottom.add(label,BorderLayout.CENTER);
		panelBottom.add(button,BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(panelBottom,BorderLayout.SOUTH);
		
		rootWatcher.addActionListener(this);
		hddMapUpdated();
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void p(Object obj) throws Exception
	{
		root = (File) obj;
		refresh();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("filter_name"))
		{
			filterName = (F) obj;
			refresh();
			return;
		}
		if(key.equals("filter_late"))
		{
			filterLate = (F) obj;
			table.repaint();
			return;
		}
		if(key.equals("color_ratio"))
		{
			colorRatio = (Color) obj;
			if(colorRatio==null) colorRatio = COLOR_RATIO;
			table.repaint();
			return;
		}
		if(key.equals("color_late"))
		{
			colorLate = (Color) obj;
			if(colorLate==null) colorLate = COLOR_LATE;
			table.repaint();
			return;
		}
		if(key.equals("desc_map"))
		{
			descMap = (Map) obj;
			model.fireTableDataChanged();
			return;
		}
		if(key.equals("width_map"))
		{
			widthMap = (Map) obj;
			computeWidths();
			model.fireTableDataChanged();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	private void refresh() throws Exception
	{
		list = new ArrayList();
		totalSpace = 0;
		freeSpace = 0;
		
		if(root==null || !root.isDirectory())
		{
			label.setText(" ");
			model.fireTableDataChanged();
			return;
		}

		File[] files = (File[]) findListing.t(root);
		if(files!=null) for(File file : files)
		{
			Map prop = (Map) readProp.t(file);
			String name = get(prop,KEY_NAME);
			
			if(filterName==null || filterName.f(name))
			{
				long total = Long.parseLong(get(prop,KEY_TOTAL));
				long free = Long.parseLong(get(prop,KEY_FREE));
				
				totalSpace += total;
				freeSpace += free;
				list.add(prop);
			}
		}
		
		label.setText(labelDisplay());
		label.setToolTipText(totalSpace+" - "+freeSpace);
		
		model.fireTableDataChanged();
	}
	
	private String labelDisplay() throws Exception
	{
		String totalSpaceS = format(totalSpace);
		String freeSpaceS = format(freeSpace);
		
		String percentS = percentS(freeSpace,totalSpace);
		
		StringBuffer b = new StringBuffer();
		b.append(" ");
		if(list!=null) b.append(list.size()+" disks - ");
		b.append("Total: "+totalSpaceS+" - ");
		b.append("Free: "+freeSpaceS+" - ");
		b.append(percentS);
		
		return b.toString();
	}
	
	private class TableModel1 extends AbstractTableModel
	{
		public int getColumnCount() {return COL_NB;}
		public int getRowCount() {return list==null ? 0 : list.size();}
		public boolean isCellEditable(int x, int y) {return false;}
		
		public Class getColumnClass(int y)
		{
			if(y==3) return Long.class;
			if(y==4) return Long.class;
			if(y==5) return Double.class;
			return String.class;
		}
		
		public String getColumnName(int y)
		{
			if(y==0) return "State";
			if(y==1) return "Serial";
			if(y==2) return "Name";
			if(y==3) return "Total";
			if(y==4) return "Free";
			if(y==5) return "Ratio";
			if(y==6) return "Scanned";
			if(y==7) return "Path";
			if(y==8) return "Dirs";
			if(y==9) return "Description";
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			if(list==null || list.isEmpty()) return null;
			
			Map prop = (Map) list.get(x);
			String name = get(prop,KEY_NAME);
			String serial = get(prop,KEY_SERIAL);
			String state = findState(name+"|"+serial);
		
			if(y==0) return state;
			if(y==1) return serial;
			if(y==2) return name;
			if(y==3) return Long.valueOf(get(prop,KEY_TOTAL));
			if(y==4) return Long.valueOf(get(prop,KEY_FREE));
			if(y==5) return Double.valueOf(get(prop,KEY_RATIO));
			if(y==6) return get(prop,KEY_TIME);
			if(y==7) return get(prop,KEY_PATH);
			if(y==8) return get(prop,KEY_DIRS);
			if(y==9) return get(descMap,name);
			
			return null;
		}
	}
	
	private class TableCellRendererString extends JLabel implements TableCellRenderer
	{
		public TableCellRendererString()
		{
			setOpaque(true);
			setBackground(Color.WHITE);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int x, int y)
		{
			setBackground(background(isSelected));
			String s = (String) value;
			
			if(y==0) //state
			{
				setText("");
				setIcon(iconState(s));
			}
			else if(y==6) //time
			{
				setText(s);
				setIcon(null);
				setForeground(findForegroundForTime(s));
			}
			else
			{
				setText(s);
				setIcon(null);
				setForeground(Color.BLACK);
			}
			return this;
		}
	}
	
	private class TableCellRendererLong extends JLabel implements TableCellRenderer
	{
		public TableCellRendererLong()
		{
			setOpaque(true);
			setBackground(Color.WHITE);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int x, int y)
		{
			setText(format((Long) value));
			setBackground(background(isSelected));
			return this;
		}
	}
	
	private class TableCellRendererRatio extends JProgressBar implements TableCellRenderer
	{
		public TableCellRendererRatio()
		{
			setBorderPainted(false);
			setStringPainted(true);
			setMinimum(0);
			setMaximum(1000);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int x, int y)
		{
			Double doubleVal = (Double) value;
			int intVal = (int)(doubleVal.doubleValue()*1000);
			setValue(intVal);
			setForeground(colorRatio);
			setBackground(background(isSelected));
			return this;
		}
	}
	
	private String get(Map prop, String key)
	{
		if(prop==null) return null;
		if(!prop.containsKey(key)) return null;
		return (String) prop.get(key);
	}
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	private Color background(boolean isSelected)
	{return isSelected ? COLOR_SELECTED : Color.WHITE;}
	
	private String format(Long value)
	{
		try{return (String) formatter.t(value);}
		catch(Exception e){return "###"+e;}
	}
	
	private String percentS(long value, long total)
	{
		if(total==0) return "";
		double p = (double)value/(double)total;
		
		try{return (String) percent.t(p);}
		catch(Exception e){return "###"+e;}
	}
	
	private Color findForegroundForTime(String s)
	{
		try{return (filterLate!=null && filterLate.f(s)) ? colorLate : Color.BLACK;}
		catch(Exception e){return Color.RED;}
	}
	
	private String findState(String hddID)
	{
		if(hddMap==null) return STATE_OFFLINE;
		return hddMap.containsKey(hddID) ? STATE_ONLINE : STATE_OFFLINE;
	}
	
	private Icon iconState(String state)
	{
		if(state.equals(STATE_ONLINE)) return iconOnline;
		if(state.equals(STATE_OFFLINE)) return iconOffline;
		return null;
	}
	
	private void computeWidths()
	{
		for(int i=0;i<COL_NB;i++)
		{
			String key = ""+i;
			if(widthMap.containsKey(key))
			{
				int width = Integer.parseInt(get(widthMap,key));
				resizeColumns(i,width);
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{hddMapUpdated();}
	
	private void hddMapUpdated()
	{
		try
		{
			hddMap = (Map) findHddMap.g();
			model.fireTableDataChanged();
		}
		catch(Exception e)
		{Outside.err(this,"hddMapUpdated()",e);}
	}
	
	private void scan()
	{
		try
		{
			if(root==null) return;
			
			hddMap = (Map) findHddMap.g();
			Iterator it = hddMap.keySet().iterator();
			while(it.hasNext())
			{
				String id = (String) it.next();
				String[] nn = id.split("\\|");
				String name = nn[0];
				String serial = nn[1];
				
				if(filterName==null || filterName.f(name))
				{
					File hdd = (File) hddMap.get(id);
					performScan.p(new Object[]{root,hdd,name,serial});
				}
			}
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"scan()",e);}
	}
}