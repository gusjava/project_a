package a.entity.gus06.sys.filemanagement1.gui.gui1_1.roots;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.io.File;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JComponent;
import java.util.Iterator;

public class EntityImpl implements Entity, ActionListener, I, P, ListSelectionListener {

	public String creationDate() {return "20191107";}
	
	public static final String KEY_HDD = "HDD";
	public static final String KEY_PATH = "PATH";
	public static final String KEY_STARTTIME = "STARTTIME";
	public static final String KEY_DURATION = "DURATION";
	public static final String KEY_FILENB = "FILENB";
	public static final String KEY_SPACE = "SPACE";
	public static final String KEY_ERROR = "ERROR";
	
	public static final String STATE_OFFLINE = "offline";
	public static final String STATE_ONLINE = "online";
	public static final String STATE_ONLINE_E = "online_e";
	public static final String STATE_RUNNING = "running";
	public static final String STATE_RUNNING_E = "running_e";
	
	public static final Color COLOR_ONLINE = new Color(222,238,255);
	public static final Color COLOR_SELECTED = Color.LIGHT_GRAY;
	public static final Color COLOR_LATE = Color.ORANGE;



	private Service findListing;
	private Service readProp;
	private Service tooltip;
	private Service buildButton;
	private Service performAdd;
	private Service performDelete;
	private Service performEdit;
	private Service performRename;
	private Service buildScanner;
	private Service openDir;
	private Service rootWatcher;
	private Service buildRootNameMap;
	private Service formatSize;
	private Service formatDuration;
	private Service tableTooltip;
	private Service executeAfter;
	private Service keyTypedWith;
	
	private JPanel panel;
	private JTable table;
	private JLabel labelInfos;
	private JLabel labelRootPath;
	
	private JButton button_add;
	private JButton button_delete;
	private JButton button_edit;
	private JButton button_rename;
	private JButton button_scan;
	private JButton button_open;
	
	
	private Map propMap;
	private List keys;
	private TableModel1 model;
	private Object engine;
	
	private Map scanMapRunning;
	private Map scanMapFailed;
	private Map rootMapName;
	
	private Icon iconOffline;
	private Icon iconOnline;
	private Icon iconOnlineE;
	private Icon iconRunning;
	private Icon iconRunningE;

	private F filterLate;
	private Color colorLate = COLOR_LATE;


	public EntityImpl() throws Exception
	{
		findListing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		tooltip = Outside.service(this,"gus.x.swing.table.cust.tooltip1");
		buildButton = Outside.service(this,"gus06.swing.button.build2.execute");
		performAdd = Outside.service(this,"gus06.sys.filemanagement1.perform.root.add");
		performDelete = Outside.service(this,"gus06.sys.filemanagement1.perform.root.delete");
		performEdit = Outside.service(this,"gus06.sys.filemanagement1.perform.root.edit");
		performRename = Outside.service(this,"gus06.sys.filemanagement1.perform.root.rename");
		buildScanner = Outside.service(this,"gus06.sys.filemanagement1.scan.builder");
		openDir = Outside.service(this,"gus06.awt.desktop.open");
		rootWatcher = Outside.service(this,"gus06.watching.dir.roots");
		buildRootNameMap = Outside.service(this,"gus06.file.roots.map.name");
		formatSize = Outside.service(this,"gus06.string.transform.format.datasize.en");
		formatDuration = Outside.service(this,"gus06.string.transform.format.duration.ms.en");
		tableTooltip = Outside.service(this,"gus.x.swing.table.cust.tooltip1");
		executeAfter = Outside.service(this,"gus06.thread.start.executeafter");
		keyTypedWith = Outside.service(this,"gus06.swing.comp.cust3.on.keytyped.with.execute");
		
		iconOffline = (Icon) Outside.resource(this,"icon#UTIL_disk_offline");
		iconOnline = (Icon) Outside.resource(this,"icon#UTIL_disk_online");
		iconOnlineE = (Icon) Outside.resource(this,"icon#UTIL_disk_online_error");
		iconRunning = (Icon) Outside.resource(this,"icon#UTIL_running");
		iconRunningE = (Icon) Outside.resource(this,"icon#UTIL_running_error");
		
		scanMapRunning = new HashMap();
		scanMapFailed = new HashMap();
		
		model = new TableModel1();
		table = new JTable(model);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.setShowGrid(false);
		table.getSelectionModel().addListSelectionListener(this);
		table.setDefaultRenderer(String.class, new TableCellRendererString());
		tableTooltip.p(table);

		resizeColumns(0,40);
		resizeColumns(4,110);
		resizeColumns(5,100);
		resizeColumns(6,70);
		resizeColumns(7,70);
		
		button_add = build(this::add,"ACTION_crud_add#Add");
		button_delete = build(this::delete,"ACTION_crud_delete#Delete");
		button_edit = build(this::edit,"ACTION_crud_edit#Edit");
		button_rename = build(this::rename,"ACTION_crud_rename#Rename");
		button_scan = build(this::scan,"ACTION_scan#Scan");
		button_open = build(this::open,"ACTION_openDir#Open dir");
		
		labelInfos = new JLabel(" ");
		labelRootPath = new JLabel(" ");
		
		JPanel panel_buttons = new JPanel(new GridLayout(1,0,5,5));
		panel_buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel_buttons.add(button_add);
		panel_buttons.add(button_delete);
		panel_buttons.add(button_edit);
		panel_buttons.add(button_rename);
		panel_buttons.add(button_scan);
		panel_buttons.add(button_open);
		
		JPanel panel_labels = new JPanel(new GridLayout(0,1));
		panel_labels.add(labelInfos);
		panel_labels.add(labelRootPath);
		
		panel = cs(cs(new JScrollPane(table), panel_labels), panel_buttons);
		
		rootChanged();
		rootWatcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{rootChanged();}
		});
		
		keyTypedWith.p(new Object[]{table,"0",(E) this::scan});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private JPanel cs(JComponent c, JComponent s)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(c,BorderLayout.CENTER);
		p.add(s,BorderLayout.SOUTH);
		return p;
	}
	
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	
	private JButton build(E execute, String display) throws Exception
	{return (JButton) buildButton.t(new Object[]{execute,display});}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(engine!=null) ((S)engine).removeActionListener(this);
		engine = obj;
		if(engine!=null) ((S)engine).addActionListener(this);
		initConfig();
		
		reload();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("configChanged()")) initConfig();
		if(s.equals("rootChanged()")) reload();
	}
	
	
	
	private void initConfig()
	{
		try
		{
			if(engine==null) return;
			
			File rootDir = (File) ((R) engine).r("root");
			filterLate = (F) ((R) engine).r("config:filter.late");
			colorLate = (Color) ((R) engine).r("config:color.late");
			if(colorLate==null) colorLate = COLOR_LATE;
			
			table.repaint();
			labelRootPath.setText("   Dir: "+rootDir.getAbsolutePath());
		}
		catch(Exception e)
		{Outside.err(this,"initConfig()",e);}
	}
	
	
	private void reload()
	{
		try
		{
			if(engine==null) return;
			
			propMap = (Map) ((R) engine).r("mapRoots");
			keys = new ArrayList(propMap.keySet());
			Collections.sort(keys);
			model.fireTableDataChanged();
			refreshLabel();
			refreshButtons();
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	
	
	
	private boolean scanRunning(String name)
	{
		if(name==null) return false;
		return scanMapRunning.containsKey(name);
	}
	
	private boolean scanFailed(String name)
	{return scanMapFailed.containsKey(name);}
	
	
	
	private void add()
	{
		try
		{
			File dirRoots = findDirRoots();
			boolean done = performAdd.f(dirRoots);
			if(!done) return;
			
			((V)engine).v("rootChanged",null);
		}
		catch(Exception e)
		{Outside.err(this,"add()",e);}
	}
	
	private void delete()
	{
		try
		{
			String selected = getSelectedName();
			if(selected==null) return;
			
			boolean done = performDelete.f(new Object[]{engine,selected});
			if(!done) return;
			
			((V)engine).v("rootChanged",null);
		}
		catch(Exception e)
		{Outside.err(this,"delete()",e);}
	}
	
	private void edit()
	{
		try
		{
			String selected = getSelectedName();
			if(selected==null) return;
			
			File dirRoots = findDirRoots();
			boolean done = performEdit.f(new Object[]{dirRoots,selected});
			if(!done) return;
			
			((V)engine).v("rootChanged",null);
		}
		catch(Exception e)
		{Outside.err(this,"edit()",e);}
	}
	
	private void rename()
	{
		try
		{
			String selected = getSelectedName();
			if(selected==null) return;
			
			boolean done = performRename.f(new Object[]{engine,selected});
			if(!done) return;
			
			((V)engine).v("rootChanged",null);
		}
		catch(Exception e)
		{Outside.err(this,"rename()",e);}
	}
	
	private void scan()
	{
		try
		{
			List<String> selectedNames = getSelectedNames();
			if(selectedNames==null || selectedNames.isEmpty()) return;
			
			List scannerList = new ArrayList();
			for(String selectedName : selectedNames)
			{
				Object scanner = buildScanner.t(new Object[]{engine,selectedName});
				scannerList.add(scanner);
			}
			new ScannerHolder(selectedNames,scannerList);
		}
		catch(Exception e)
		{Outside.err(this,"scan()",e);}
	}
	
	
	private void open()
	{
		try
		{
			File dir = getSelectedDir();
			if(dir==null) return;
			openDir.p(dir);
		}
		catch(Exception e)
		{Outside.err(this,"open()",e);}
	}
	
	
	
	private class ScannerHolder implements ActionListener, E, Runnable
	{
		private List<String> names;
		private List scanners;
		
		private String currentName;
		private Object currentScanner;
		
		private Thread t;
		
		public ScannerHolder(List<String> names, List scanners) throws Exception
		{
			this.names = names;
			this.scanners = scanners;
			
			String threadName = "THREAD_"+EntityImpl.class.getName()+"_scans";
			t = new Thread(this ,threadName);
			executeAfter.p(new Object[]{t, this});
		}
		
		public void run()
		{
			try
			{
				int number = names.size();
				for(int i=0;i<number;i++)
				{
					currentName = names.get(i);
					currentScanner = scanners.get(i);
					
					((S) currentScanner).addActionListener(this);
					((Runnable) currentScanner).run();
					((S) currentScanner).removeActionListener(this);
				}
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this, "run()", e);}
		}
		
		public void e() throws Exception
		{
			if(!scanRunning(currentName)) return;
			
			scanMapRunning.remove(currentName);
			scanMapFailed.put(currentName,this);
			reload();
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String s = e.getActionCommand();
			if(s.equals("started()")) started();
			else if(s.equals("done()")) done();
			else if(s.equals("failed()")) failed();
		}
		
		private void started()
		{
			scanMapRunning.put(currentName,this);
			scanMapFailed.remove(currentName);
			reload();
		}
		
		private void done()
		{
			scanMapRunning.remove(currentName);
			scanMapFailed.remove(currentName);
			reload();
		}
		
		private void failed()
		{
			scanMapRunning.remove(currentName);
			scanMapFailed.put(currentName,this);
			reload();
		}
	}
	
	
	
	
	private class TableModel1 extends AbstractTableModel
	{
		public int getColumnCount() {return 8;}
		public int getRowCount() {return keys==null ? 0 : keys.size();}
		public boolean isCellEditable(int x, int y) {return false;}
		
		public Class getColumnClass(int y)
		{return String.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "State";
			if(y==1) return "Name";
			if(y==2) return "HDD";
			if(y==3) return "Path";
			if(y==4) return "Start date";
			if(y==5) return "Duration";
			if(y==6) return "Files";
			if(y==7) return "Space";
			
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			if(keys==null || keys.isEmpty()) return null;
			
			String key = (String) keys.get(x);
			
			Map prop = (Map) propMap.get(key);
			String hdd = get(prop,KEY_HDD);
			String path = get(prop,KEY_PATH);
		
			if(y==0) return findState(key,hdd,path);
			if(y==1) return key;
			if(y==2) return hdd;
			if(y==3) return path;
			if(y==4) return get(prop,KEY_STARTTIME);
			if(y==5) return get(prop,KEY_DURATION);
			if(y==6) return get(prop,KEY_FILENB);
			if(y==7) return get(prop,KEY_SPACE);
			
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
			String s = (String) value;
			
			setBackground(background(x, isSelected));
			setForeground(Color.BLACK);
			
			if(s==null)
			{
				setText("###");
				setIcon(null);
				return this;
			}
			
			if(y==0) //state
			{
				setText("");
				setIcon(iconState(x));
			}
			else if(y==4) //start date
			{
				setText(s);
				setIcon(null);
				setForeground(findForegroundForTime(s));
			}
			else if(y==5) //duration
			{
				setText(formatDuration(s));
				setIcon(null);
			}
			else if(y==7) //space
			{
				setText(formatSize(s));
				setIcon(null);
			}
			else
			{
				setText(s);
				setIcon(null);
			}
			return this;
		}
	}
	
	
	
	private Color background(int x, boolean isSelected)
	{
		String state = (String) table.getValueAt(x,0);
		if(isSelected) return COLOR_SELECTED;
		if(state.equals(STATE_ONLINE)) return COLOR_ONLINE;
		return Color.WHITE;
	}
	
	
	private Icon iconState(int x)
	{
		String state = (String) table.getValueAt(x,0);
		if(state.equals(STATE_OFFLINE)) return iconOffline;
		if(state.equals(STATE_ONLINE)) return iconOnline;
		if(state.equals(STATE_ONLINE_E)) return iconOnlineE;
		if(state.equals(STATE_RUNNING)) return iconRunning;
		if(state.equals(STATE_RUNNING_E)) return iconRunningE;
		return null;
	}
	
	private String formatSize(Object data)
	{
		try{return (String) formatSize.t(data);}
		catch(Exception e){return ""+data;}
	}
	
	private String formatDuration(Object data)
	{
		try{return (String) formatDuration.t(data);}
		catch(Exception e){return ""+data;}
	}
	
	
	
	
	private String get(Map m, String key)
	{
		if(!m.containsKey(key)) return null;
		return (String) m.get(key);
	}
	
	
	private long toLong(String s)
	{
		if(s==null) return 0;
		try{return Long.parseLong(s);}
		catch(NumberFormatException e)
		{return 0;}
	}
	
	
	private File findDirRoots() throws Exception
	{
		if(engine==null) return null;
		return (File) ((R) engine).r("dirRoots");
	}
	
	
	
	
	
	
	public void valueChanged(ListSelectionEvent e)
	{refreshButtons();}
	
	
	private boolean hasSelection()
	{return !table.getSelectionModel().isSelectionEmpty();}
	
	
	
	
	private String getSelectedName()
	{
		if(!hasSelection()) return null;
		int x = table.getSelectedRow();
		return (String) table.getValueAt(x,1);
	}
	
	private List<String> getSelectedNames()
	{
		if(!hasSelection()) return null;
		List<String> selectedNames = new ArrayList<>();	
		int[] selectedRows = table.getSelectedRows();
		for (int rowIndex : selectedRows)
		{
			String name = (String) table.getValueAt(rowIndex, 1);
			selectedNames.add(name);
		}
		return selectedNames;
	}
	
	
	private File getSelectedDir()
	{
		String selected = getSelectedName();
		if(selected==null) return null;
		
		Map prop = (Map) propMap.get(selected);
		String hdd = get(prop,KEY_HDD);
		String path = get(prop,KEY_PATH);
		
		File dir0 = (File) rootMapName.get(hdd);
		File dir = new File(dir0,path);
		
		return dir.isDirectory() ? dir : null;
	}
	
	
	private boolean hasSelectedDir()
	{return getSelectedDir()!=null;}
	
	private boolean hasRunningDir()
	{return scanRunning(getSelectedName());}
	
	
	
	private void rootChanged()
	{
		try
		{
			rootMapName = (Map) buildRootNameMap.g();
			model.fireTableDataChanged();
			refreshButtons();
		}
		catch(Exception e)
		{Outside.err(this,"rootChanged()",e);}
	}
	
	
	
	
	
	
	private void refreshButtons()
	{
		boolean hasSelection = hasSelection();
		
		button_edit.setEnabled(hasSelection);
		button_rename.setEnabled(hasSelection);
		button_delete.setEnabled(hasSelection);
		button_open.setEnabled(hasSelection && hasSelectedDir());
		button_scan.setEnabled(hasSelection && hasSelectedDir() && !hasRunningDir());
	}
	
	
	
	
	private void refreshLabel()
	{
		long totalFileNb = 0;
		long totalSpace = 0;
		
		Iterator it = propMap.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Map prop = (Map) propMap.get(key);
			
			totalFileNb += toLong(get(prop,KEY_FILENB));
			totalSpace += toLong(get(prop,KEY_SPACE));
		}
		
		StringBuffer b = new StringBuffer();
		b.append("   Roots: "+keys.size());
		b.append("   Files: "+totalFileNb);
		b.append("   Space: "+formatSize(totalSpace));
		
		labelInfos.setText(b.toString());
	}
	
	
	
	private String findState(String key, String hdd, String path)
	{
		try
		{
			if(rootMapName==null) return STATE_OFFLINE;
			if(!rootMapName.containsKey(hdd)) return STATE_OFFLINE;
			
			File d = (File) rootMapName.get(hdd);
			File d1 = new File(d,path);
			if(!d1.isDirectory()) return STATE_ONLINE_E;
			
			if(scanRunning(key)) return STATE_RUNNING;
			if(scanFailed(key)) return STATE_RUNNING_E;
			return STATE_ONLINE;
		}
		catch(Exception e)
		{Outside.err(this,"findState(String,String,String)",e);}
		return "###";
	}
	
	
	private Color findForegroundForTime(String s)
	{
		try{return (filterLate!=null && filterLate.f(s)) ? colorLate : Color.BLACK;}
		catch(Exception e){return Color.RED;}
	}
}