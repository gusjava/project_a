package a.entity.gus06.sys.dirsearch1.gui.search.resultpanel;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.JToolBar;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Rectangle;
import javax.swing.Action;

public class EntityImpl extends S1 implements Entity, ActionListener, MouseListener, ListSelectionListener, I, E, V, G {

	public String creationDate() {return "20191228";}
	
	public static final String KEY_FILE = "file";
	public static final String KEY_ROOT_LIST = "root_list";
	public static final String KEY_ROOT_INDEX = "root_index";
	
	public static final String KEY_POS = "pos";
	public static final String KEY_BLOCK = "block";
	public static final String KEY_NAME = "name";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_SECTIONS = "sections";
	public static final String KEY_EXTRACTOR = "extractor";
	public static final String KEY_LINENB = "line_nb";
	
	public static final String ACTIONID_COPY_SELECTED = "ACTION_copySelected#Copy selected";
	public static final String ACTIONID_COPY_ALL = "ACTION_copyAll#Copy all";
	
	public static final int GAP = 2;
	public static final Border BORDER = BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP);
	public static final Color SELECTION_COLOR = new Color(234,234,234);

	public static final Font FONT_P = new JLabel().getFont().deriveFont(Font.PLAIN);
	public static final Font FONT_B = new JLabel().getFont().deriveFont(Font.BOLD);
	
	public static final int COL_REPARTITION = 4;
	public static final int COL_NUMBER = 5;
	
	private Service findIcon;
	private Service executeDelayed;
	private Service tableTooltip;
	private Service clipboard;
	private Service buildAction;
	private Service toolbar;
	
	private JPanel panel;
	private JTable table;
	private TableModel0 model;
	
	private JLabel labelNb;
	private JToolBar barCounts;
	private JToolBar barActions;
	
	private Object engine;
	private Map map;
	private List keys;
	
	private List listExtr;
	private List listColor;
	private Map mapCounts;
	
	private Action actionCopySelected;
	private Action actionCopyAll;
	
	private E refreshE;
	private E selectionE;
	
	private Selection selection;
	private boolean justClicked = false;

	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.file.icon.t1");
		executeDelayed = Outside.service(this,"gus06.time.execute.delayed.ms100.thr");
		tableTooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		clipboard = Outside.service(this,"gus.y.clipboard1.files");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		toolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		
		map = new HashMap();
		keys = new ArrayList();
		mapCounts = new HashMap();
		
		model = new TableModel0();
		
		table = new JTable(model);
		table.setRowHeight(table.getRowHeight()+2*GAP);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(Object.class,new TableCellRenderer0());
		table.getSelectionModel().addListSelectionListener(this);
		table.addMouseListener(this);
		
		initColumnSize(2,50);
		tableTooltip.p(table);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);

		labelNb = new JLabel(" ");
		
		barCounts = new JToolBar();
		barCounts.setFloatable(false);
		
		actionCopySelected = (Action) buildAction.t(
			new Object[]{ACTIONID_COPY_SELECTED,(E) this::copySelectedFile});
		actionCopyAll = (Action) buildAction.t(
			new Object[]{ACTIONID_COPY_ALL,(E) this::copyAllFiles});
		
		barActions = (JToolBar) toolbar.i();
		barActions.add(actionCopySelected);
		barActions.add(actionCopyAll);
		
		JPanel panelBottom = new JPanel(new BorderLayout());
		panelBottom.add(labelNb,BorderLayout.WEST);
		panelBottom.add(barCounts,BorderLayout.CENTER);
		panelBottom.add(barActions,BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(panelBottom,BorderLayout.SOUTH);
		
		refreshE = (E) executeDelayed.t((E)(this::refresh));
		selectionE = (E) executeDelayed.t((E)(this::selectionChanged));
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void e() throws Exception
	{
		map.clear();
		keys.clear();
		mapCounts.clear();
		selection = null;
		refresh();
	}
	
	public Object g() throws Exception
	{
		return selection;
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine")) {initEngine(obj);return;}
		if(key.equals("listExtr")) {initListExtr(obj);return;}
		if(key.equals("widthMap")) {initWidths((Map) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	private void initEngine(Object obj) throws Exception
	{
		if(engine!=null) throw new Exception("Engine already initialized");
		engine = obj;
		((S) engine).addActionListener(this);
	}
	
	private void initListExtr(Object obj) throws Exception
	{
		listExtr = (List) obj;
		listColor = new ArrayList();
		
		for(int i=0;i<listExtr.size();i++)
		{
			R extr = (R) listExtr.get(i);
			Color color = (Color) extr.r("color");
			listColor.add(color);
		}
	}
	
	private void initWidths(Map widthMap)
	{
		for(int i=0;i<COL_NUMBER;i++)
		{
			String key = ""+i;
			if(widthMap.containsKey(key))
			{
				String widthS = (String) widthMap.get(key);
				int width = Integer.parseInt(widthS);
				resizeColumns(i,width);
			}
		}
		model.fireTableDataChanged();
	}
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}

	public void actionPerformed(ActionEvent e)
	{handleResult();}
	
	private void handleResult()
	{
		try
		{
			Map result = (Map) ((G)engine).g();
			File file = (File) result.get(KEY_FILE);
			List rootList = (List) result.get(KEY_ROOT_LIST);
			Integer rootIndex = (Integer) result.get(KEY_ROOT_INDEX);
			
			findResultsHolder(file,rootList,rootIndex).addResult(result);
			
			labelNb.setText(getNbDisplay());
			model.fireTableDataChanged();
			refreshE.e();
		}
		catch(Exception e)
		{Outside.err(this,"handleResult()",e);}
	}
	
	private void refresh()
	{
		labelNb.setText(getNbDisplay());
		refreshCountBar(barCounts,mapCounts);
		model.fireTableDataChanged();
	}
	
	private ResultsHolder findResultsHolder(File file, List rootList, int rootIndex) throws Exception
	{
		String key = file.getAbsolutePath();
		if(!map.containsKey(key)) 
		{
			map.put(key, new ResultsHolder(file,rootList,rootIndex));
			keys.add(key);
		}
		return (ResultsHolder) map.get(key);
	}
	
	private String getNbDisplay()
	{
		int nb = map.size();
		if(nb==0) return " ";
		if(nb==1) return " 1 file";
		return " "+nb+" files";
	}
	
	private class ResultsHolder
	{
		private File file;
		private List rootList;
		private int rootIndex;
		private File root;
		
		private int lineNb;
		private String name;
		private String location;
		private String locationDisplay;
		private Icon icon;
		
		private List listName;
		private List listLocation;
		private List listBlock;
		
		private Map mapCountsName;
		private Map mapCountsLocation;
		private Map mapCountsBlock;
		
		private Map mapPosColors;
		private Map mapPosColorsSections;
		
		public ResultsHolder(File file, List rootList, int rootIndex) throws Exception
		{
			this.file = file;
			this.rootList = rootList;
			this.rootIndex = rootIndex;
			
			root = (File) rootList.get(rootIndex);
			
			lineNb = -1;
			name = file.getName();
			icon = (Icon) findIcon.t(file);
			
			listName = new ArrayList();
			listLocation = new ArrayList();
			listBlock = new ArrayList();
			
			mapCountsName = new HashMap();
			mapCountsLocation = new HashMap();
			mapCountsBlock = new HashMap();
			
			mapPosColors = new HashMap();
			mapPosColorsSections = new HashMap();
			
			if(root.isFile())
			{
				if(!root.equals(file)) throw new Exception("Invalid root: "+root+" for file: "+file);
				location = file.getAbsolutePath();
				locationDisplay = root.getName();
			}
			else
			{
				location = file.getParentFile().getAbsolutePath();
				String rootPath = root.getAbsolutePath();
				locationDisplay = location.substring(rootPath.length());
			}
			
			if(rootList.size()>1)
			locationDisplay = "["+(rootIndex+1)+"] "+locationDisplay;
		}
		
		public void addResult(Map result) throws Exception
		{
			if(result.containsKey(KEY_LINENB))
			{
				lineNb = (int) result.get(KEY_LINENB);
			}
			if(result.containsKey(KEY_NAME))
			{
				List sections = (List) result.get(KEY_SECTIONS);
				R extr = (R) result.get(KEY_EXTRACTOR);
				
				int count = sections.size();
				Color color = (Color) extr.r("color");
				
				appendColorCount(mapCountsName,color,count);
				appendColorCount(mapCounts,color,count);
			}
			if(result.containsKey(KEY_LOCATION))
			{
				List sections = (List) result.get(KEY_SECTIONS);
				R extr = (R) result.get(KEY_EXTRACTOR);
				
				int count = sections.size();
				Color color = (Color) extr.r("color");
				
				appendColorCount(mapCountsLocation,color,count);
				appendColorCount(mapCounts,color,count);
			}
			if(result.containsKey(KEY_BLOCK))
			{
				List sections = (List) result.get(KEY_SECTIONS);
				R extr = (R) result.get(KEY_EXTRACTOR);
				int pos = (int) result.get(KEY_POS);
				
				
				int count = sections.size();
				Color color = (Color) extr.r("color");
				
				appendColorCount(mapCountsBlock,color,count);
				appendColorCount(mapCounts,color,count);
				
				if(!mapPosColors.containsKey(pos)) 
					mapPosColors.put(pos,new HashSet());
				((Set) mapPosColors.get(pos)).add(color);
				
				if(!mapPosColorsSections.containsKey(pos)) 
					mapPosColorsSections.put(pos,new HashSet());
				((Set) mapPosColorsSections.get(pos)).add(new Object[]{color,sections});
			}
		}
		
		private void appendColorCount(Map map, Color color, int count) throws Exception
		{
			if(!map.containsKey(color)) map.put(color,count);
			else
			{
				int previousCount = (int) map.get(color);
				map.put(color,previousCount+count);
			}
		}
		
		private boolean hasAllOfThem()
		{
			return mapCountsBlock.size()==listColor.size();
		}
	}
	
	private class Selection implements R
	{
		private ResultsHolder result;
		private int pos;
		
		public Selection(ResultsHolder result, int pos)
		{
			this.result = result;
			this.pos = pos;
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("pos_color_section")) return result.mapPosColorsSections;
			if(key.equals("file")) return result.file;
			if(key.equals("pos")) return pos;
			
			if(key.equals("keys")) return new String[]{"pos_color_section","file","pos"};
			throw new Exception("Unknown key: "+key);
		}
	}
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return COL_NUMBER;}
		public int getRowCount() {return keys.size();}
		public Class getColumnClass(int y) {return Object.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "location";
			if(y==1) return "file name";
			if(y==2) return "lines";
			if(y==3) return "counts";
			if(y==4) return "repartition";
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			if(keys.size()<=x) return null;
			
			String key = (String) keys.get(x);
			return (ResultsHolder) map.get(key);
		}
	}
	
	private class TableCellRenderer0 implements TableCellRenderer
	{
		private JLabel label;
		private JToolBar barCounts;
		private JLabelRepartition labelRep;
		
		private Font font_p;
		private Font font_b;
		
		public TableCellRenderer0()
		{
			super();
			
			label = new JLabel();
			label.setOpaque(true);
			label.setBackground(Color.WHITE);
			label.setBorder(BORDER);
			
			font_p = label.getFont().deriveFont(Font.PLAIN);
			font_b = label.getFont().deriveFont(Font.BOLD);
			
			barCounts = new JToolBar();
			barCounts.setBorder(BORDER);
			
			labelRep = new JLabelRepartition();
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			ResultsHolder holder = (ResultsHolder) value;
			
			if(column==0) return column0(holder,isSelected);
			if(column==1) return column1(holder,isSelected);
			if(column==2) return column2(holder,isSelected);
			if(column==3) return column3(holder,isSelected);
			if(column==4) return column4(holder,isSelected);
			
			return null;
		}
		
		private JLabel column0(ResultsHolder holder, boolean isSelected)
		{
			label.setText(holder.locationDisplay);
			label.setIcon(null);
			label.setBackground(isSelected?SELECTION_COLOR:Color.WHITE);
			label.setFont(holder.hasAllOfThem() ? font_b : font_p);
			return label;
		}
		
		private JLabel column1(ResultsHolder holder, boolean isSelected)
		{
			label.setText(holder.name);
			label.setIcon(holder.icon);
			label.setBackground(isSelected?SELECTION_COLOR:Color.WHITE);
			return label;
		}
		
		private JLabel column2(ResultsHolder holder, boolean isSelected)
		{
			label.setText(" "+holder.lineNb);
			label.setIcon(null);
			label.setBackground(isSelected?SELECTION_COLOR:Color.WHITE);
			return label;
		}
		
		private JToolBar column3(ResultsHolder holder, boolean isSelected)
		{
			barCounts.setBackground(isSelected ? SELECTION_COLOR : Color.WHITE);
			refreshCountBar(barCounts,holder.mapCountsBlock);
			return barCounts;
		}
		
		private JLabel column4(ResultsHolder holder, boolean isSelected)
		{
			labelRep.setHolder(holder);
			labelRep.setBackground(isSelected?SELECTION_COLOR:Color.WHITE);
			return labelRep;
		}
	}

	private void refreshCountBar(JToolBar bar, Map map)
	{
		bar.removeAll();
		if(listColor==null) return;
			
		for(int i=0;i<listColor.size();i++)
		{
			Color color = (Color) listColor.get(i);
			if(map.containsKey(color))
			{
				int count = (int) map.get(color);
				
				JLabel l = new JLabel(" "+count);
				l.setForeground(color);
				l.setFont(FONT_B);
				bar.add(l);
			}
		}
	}
	
	private class JLabelRepartition extends JLabel
	{
		private ResultsHolder holder;
		
		public JLabelRepartition()
		{
			super();
			setOpaque(true);
		}
		
		public void setHolder(ResultsHolder holder)
		{this.holder = holder;}
		
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(getBackground());
			g2.fillRect(0,0,getWidth(),getHeight());
			
			if(listColor==null) return;
			
			double lineNb = (double)(int) holder.lineNb;
			if(lineNb<=0) return;
			
			double step = 1.0/lineNb;
			int w = (int)(step*getWidth());
			if(w==0) w=1;
			
			Iterator it = holder.mapPosColors.keySet().iterator();
			while(it.hasNext())
			{
				int pos = (int) it.next();
				double factor = (double) pos/lineNb;
				int p1 = (int)(factor*getWidth());
				
				Set cSet = (Set) holder.mapPosColors.get(pos);
				if(cSet.size()==1)
				{
					Color c = (Color) cSet.iterator().next();
					g2.setColor(c);
					g2.fillRect(p1,0,w,getHeight());
				}
				else
				{
					List cList = sortColors(cSet);
					int cNb = cList.size();
					double h = ((double) getHeight()/(double) cNb);
					for(int i=0;i<cNb;i++)
					{
						Color c = (Color) cList.get(i);
						g2.setColor(c);
						g2.fillRect(p1,(int)(h*i),w,(int) h);
					}
				}
			}
		}
	}
	
	private List sortColors(Set s)
	{
		List l = new ArrayList();
		for(int i=0;i<listColor.size();i++)
		{
			Color c = (Color) listColor.get(i);
			if(s.contains(c)) l.add(c);
		}
		return l;
	}
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	private ResultsHolder getSelectedResult()
	{
		if(keys.isEmpty()) return null;
		if(table.getSelectionModel().isSelectionEmpty()) return null;
		
		int row = table.getSelectedRow();
		if(row==-1) return null;
		
		return (ResultsHolder) table.getValueAt(row,0);
	}
	
	public void valueChanged(ListSelectionEvent e) 
	{
		if(justClicked)
		{justClicked = false;return;}
		
		ResultsHolder holder = getSelectedResult();
		if(holder==null) {selection = null;return;}
		
		selection = new Selection(holder,-1);
		triggerSelected();
	}
	
	public void mousePressed(MouseEvent e)
	{
		justClicked = true;
		
		Point p = e.getPoint();
		int row = table.rowAtPoint(p);
		int col = table.columnAtPoint(p);
		
		if(row==-1) return;
		
		ResultsHolder holder = (ResultsHolder) table.getValueAt(row,col);
		
		if(col==COL_REPARTITION)
		{
			int lineNb = holder.lineNb;
		
			Rectangle rect = table.getCellRect(row,col,false);
			double w = rect.getWidth();
			double x = p.getX()-rect.getX();
		
			int pos = (int) ((x/w)*lineNb);
			selection = new Selection(holder,pos);
		}
		else
		{
			selection = new Selection(holder,-1);
		}
		triggerSelected();
	}
	
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	
	private void triggerSelected()
	{
		try{selectionE.e();}
		catch(Exception e)
		{Outside.err(this,"triggerSelected()",e);}
	}
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	private void copySelectedFile()
	{
		try
		{
			ResultsHolder holder = getSelectedResult();
			if(holder==null) return;
			
			File file = holder.file;
			if(file==null) return;
			clipboard.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"copySelectedFile()",e);}
	}
	
	private void copyAllFiles()
	{
		try
		{
			if(keys==null) return;
			
			List files = new ArrayList();
			for(int i=0;i<keys.size();i++)
			{
				String key = (String) keys.get(i);
				ResultsHolder holder = (ResultsHolder) map.get(key);
				files.add(holder.file);
			}
			clipboard.p(files);
		}
		catch(Exception e)
		{Outside.err(this,"copyAllFiles()",e);}
	}
}
