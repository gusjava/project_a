package a.entity.gus06.appli.gusexplorer.config.gui1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Font;
import java.util.Iterator;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;

public class EntityImpl extends S1 implements Entity, ActionListener, KeyListener, I {

	public String creationDate() {return "20250830";}
	
	public static final Color COLOR_SELECTED = new Color(224,224,166);


	private Service fieldPersister;
	private Service performRemovePath;
	private Service performRemoveNamePath;
	private Service labelCustManager;
	private Service manager;
	private Service buildFilter;
	private Service filterList;
	private Service fileToIcon;
	private Service fileToColor;
	private Service collect;
	private Service custLabel;
	private Service tooltip;
	private Service clearCopyPasteCut;
	private Service clipboard;
	private Service join;
	
	private Service field1;
	private Service field2;
	private Service field3;
	
	private Service form1;
	private Service form2;
	private Service form3;


	private JPanel panel;
	private JPanel panelSearch;
	private JPanel panelTable;
	
	
	private TableModel0 model;
	private JTable table;
	
	private Map pathsByName;
	private Map namesByPath;
	private Map pathToIcon;
	private Map pathToColor;
	private Map pathToDisplay;
	
	private List paths;
	private List names;
	
	private List pathsFiltered;
	private List namesFiltered;



	public EntityImpl() throws Exception
	{
		fieldPersister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		performRemovePath = Outside.service(this,"gus06.appli.gusexplorer.config.perform.remove.path");
		performRemoveNamePath = Outside.service(this,"gus06.appli.gusexplorer.config.perform.remove.namepath");
		labelCustManager = Outside.service(this,"gus06.appli.gusexplorer.labelcust.manager");
		manager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
		buildFilter = Outside.service(this,"gus06.filter.string.build.oneofthem_n");
		filterList = Outside.service(this,"gus06.list.filter.build");
		fileToIcon = Outside.service(this,"gus06.file.icon.t1");
		fileToColor = Outside.service(this,"gus06.file.findcolor1");
		collect = Outside.service(this,"gus06.list.collect");
		custLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		tooltip = Outside.service(this,"gus.x.swing.table.cust.tooltip1");
		clearCopyPasteCut = Outside.service(this,"gus06.swing.comp.action.clearcopypastecut");
		clipboard = Outside.service(this,"gus06.clipboard.access");
		join = Outside.service(this,"gus06.tostring.list.join.n");
		
		field1 = Outside.service(this,"*gus06.data.editor.string.textfield.editor1-1");
		field2 = Outside.service(this,"*gus06.data.editor.string.textfield.editor1-2");
		field3 = Outside.service(this,"*gus06.data.editor.string.textfield.editor1-3");
		
		form1 = Outside.service(this,"*gus06.swing.panel.formpanel.withdisplay-1");
		form2 = Outside.service(this,"*gus06.swing.panel.formpanel.withdisplay-2");
		form3 = Outside.service(this,"*gus06.swing.panel.formpanel.withdisplay-3");
		
		
		form1.v("FILE_search#Paths", field1.i());
		form2.v("DISPLAY_search#Displays", field2.i());
		form3.v("CONFIG_search#Configs", field3.i());
		
		fieldPersister.v(getClass().getName()+"_field1", field1.i());
		fieldPersister.v(getClass().getName()+"_field2", field2.i());
		fieldPersister.v(getClass().getName()+"_field3", field3.i());
		
		model = new TableModel0();
		
		table = new JTable(model);
		table.setDefaultRenderer(String.class, new TableCellRenderer0());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setCellSelectionEnabled(true);
		table.addKeyListener(this);
		
		tooltip.p(table);
		clearCopyPasteCut.p(table);
		
		panelSearch = new JPanel(new GridLayout(1,3));
		panelSearch.add((JComponent) form1.i());
		panelSearch.add((JComponent) form2.i());
		panelSearch.add((JComponent) form3.i());
		
		panelTable = new JPanel(new BorderLayout());
		
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panelSearch, BorderLayout.NORTH);
		panel.add(panelTable, BorderLayout.CENTER);
		
		field1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{field1Edited();}
		});
		field2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{field2Edited();}
		});
		field3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{field3Edited();}
		});
		manager.addActionListener(this);
		reload();
	}
	
	
	private void refreshTable()
	{
		model.fireTableStructureChanged();
		resizeNameColumns(250, 150);
	}
	
	private void resizeNameColumns(int length0, int length1)
	{
		TableColumnModel columnModel = table.getColumnModel();
		
		TableColumn col0 = columnModel.getColumn(0);
		col0.setPreferredWidth(length0);
		
		for (int i = 1; i < columnModel.getColumnCount(); i++)
		{
			TableColumn col = columnModel.getColumn(i);
			col.setMinWidth(length1);
			col.setMaxWidth(length1);
			col.setPreferredWidth(length1);
		}
	}
	

	public void actionPerformed(ActionEvent e)
	{reload();}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private void field1Edited()
	{
		try
		{
			rebuildMaps();
			refreshTable();
		}
		catch(Exception e)
		{Outside.err(this,"field1Edited()",e);}
	}
	
	private void field2Edited()
	{
		try
		{
			rebuildMaps();
			refreshTable();
		}
		catch(Exception e)
		{Outside.err(this,"field2Edited()",e);}
	}
	
	private void field3Edited()
	{
		try
		{
			rebuildMaps();
			refreshTable();
		}
		catch(Exception e)
		{Outside.err(this,"field3Edited()",e);}
	}

	private void reload()
	{
		try
		{
			pathsByName = new HashMap();
			namesByPath = new HashMap();
			pathToIcon = new HashMap();
			pathToColor = new HashMap();
			pathToDisplay = new HashMap();
			
			names = (List) manager.g();
			for(int i=0;i<names.size();i++)
			{
				String name = (String) names.get(i);
				List files = (List) manager.r(name);
				
				T fileToPath = (T) obj->((File)obj).getAbsolutePath();
				
				List paths = (List) collect.t(new Object[]{files, fileToPath});
				pathsByName.put(name, paths);
				for(int j=0;j<files.size();j++)
				{
					File file = (File) files.get(j);
					String path = file.getAbsolutePath();
					
					if(!namesByPath.containsKey(path))
					{
						Icon icon = (Icon) fileToIcon.t(file);
						Color color = (Color) fileToColor.t(file);
						String display = (String) labelCustManager.r(path);
						
						if(!file.exists()) icon = null;
						
						pathToIcon.put(path, icon);
						pathToColor.put(path, color);
						if(display!=null) pathToDisplay.put(path, display);
						namesByPath.put(path, new ArrayList());
					}
					
					((List) namesByPath.get(path)).add(name);
				}
			}
			paths = new ArrayList(namesByPath.keySet());
			Collections.sort(paths);
			
			rebuildMaps();
			refreshTable();
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	
	
	private void rebuildMaps() throws Exception
	{
		F filter1 = (F) buildFilter.t(field1.g());
		F filter2 = (F) buildFilter.t(field2.g());
		F filter3 = (F) buildFilter.t(field3.g());
		
		// Filtrage de paths
		
		//TODO fusionner filter1 et filter2
		
		F filter1and2 = new F() {
			public boolean f(Object obj) throws Exception
			{
				String path = (String) obj;
				if(!filter1.f(path)) return false;
				
				String display = pathDisplay(path);
				if(!filter2.f(display)) return false;
				
				return true;
			}	
		};
		
		T builderPaths = (T) filterList.t(filter1and2);
		pathsFiltered = (List) builderPaths.t(paths);
		
		// Filtrage de names
		
		T builderNames = (T) filterList.t(filter3);
		namesFiltered = (List) builderNames.t(names);
		
		// On retire les entr�es vides
		
		namesFiltered.removeIf(name -> {
			List pathsForName = (List) pathsByName.get(name);
			return Collections.disjoint(pathsForName, pathsFiltered);
		});
		pathsFiltered.removeIf(path -> {
			List namesForPath = (List) namesByPath.get(path);
			return Collections.disjoint(namesForPath, namesFiltered);
		});
	}


	private class TableModel0 extends AbstractTableModel
	{
		public TableModel0(){}
    	
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{return String.class;}
    	
		public int getRowCount()
		{
			if(pathsFiltered==null) return 0;
			return pathsFiltered.size();
		}
		
		public int getColumnCount()
		{
			if(namesFiltered==null) return 0;
			return namesFiltered.size()+1;
		}
		
		public String getColumnName(int y)
		{
			if(names==null) return null;
			if(y==0) return "PATH";
			return (String) namesFiltered.get(y-1);
		}
		
		public Object getValueAt(int x, int y)
		{
			if(paths==null) return null;
			String path = pathAt(x);
			if(y==0) return path;
			
			String name = (String) namesFiltered.get(y-1);
			List names = (List) namesByPath.get(path);
			if(!names.contains(name)) return "";
			
			return path;
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

			font_p = getFont().deriveFont(Font.PLAIN);
			font_b = getFont().deriveFont(Font.BOLD);
		}
		
		public Component getTableCellRendererComponent(
				JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) {
				
			String path = (String) value;	
			setBackground(isSelected ? COLOR_SELECTED : Color.WHITE);
			
			if(path==null)
			{
				setText("");
				setIcon(null);
				return this;
			}
			
			if(column==0)
			{
				setText(path);
				setIcon(pathIcon(path));
				setForeground(pathColor(path));
				setFont(font_p);
				return this;
			}
			
			setForeground(pathColor(path));
			String display = pathDisplay(path);
			if(display!=null)
			{
				repaintLabel(this, display);
				setFont(font_b);
				return this;
			}
			
			setText(fileName(path));
			setIcon(pathIcon(path));
			setFont(font_p);
			return this;
		}
	}
	
	
	private void repaintLabel(JLabel label, String display)
	{
		try{custLabel.v(display, label);}
		catch(Exception e)
		{Outside.err(this,"repaintLabel(JLabel,String)",e);}
	}
	
	private String fileName(String path)
	{
		if(path.contains("\\"))
		{
			String[] n = path.split("\\\\");
			path = n[n.length-1];
		}
		if(path.contains("/"))
		{
			String[] n = path.split("/");
			path = n[n.length-1];
		}
		return path;
	}
	
	private Icon pathIcon(String path)
	{
		return (Icon) pathToIcon.get(path);
	}
	
	private Color pathColor(String path)
	{
		return (Color) pathToColor.get(path);
	}
	
	private String pathDisplay(String path)
	{
		return (String) pathToDisplay.get(path);
	}
	
	private String pathAt(int index)
	{
		if(pathsFiltered.size()<=index) return null;
		return (String) pathsFiltered.get(index);
	}
	
	private String nameAt(int index)
	{
		if(namesFiltered.size()<=index) return null;
		return (String) namesFiltered.get(index);
	}
	
	
	
	
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if(e.isControlDown())
		{
			if(code == KeyEvent.VK_C) keyCtrlC();
			if(code == KeyEvent.VK_V) keyCtrlV();
			if(code == KeyEvent.VK_X) keyCtrlX();
			if(code == KeyEvent.VK_DELETE) keyCtrlDelete();
			if(code == KeyEvent.VK_F1) keyCtrlF1();
			if(code == KeyEvent.VK_F2) keyCtrlF2();
			if(code == KeyEvent.VK_F3) keyCtrlF3();
			if(code == KeyEvent.VK_F4) keyCtrlF4();
			if(code == KeyEvent.VK_F5) keyCtrlF5();
                }
		if(e.isAltDown())
		{
			if(code == KeyEvent.VK_C) keyAltC();
			if(code == KeyEvent.VK_V) keyAltV();
			if(code == KeyEvent.VK_X) keyAltX();
			if(code == KeyEvent.VK_DELETE) keyAltDelete();
			if(code == KeyEvent.VK_F1) keyAltF1();
			if(code == KeyEvent.VK_F2) keyAltF2();
			if(code == KeyEvent.VK_F3) keyAltF3();
			if(code == KeyEvent.VK_F4) keyAltF4();
			if(code == KeyEvent.VK_F5) keyAltF5();
                }
		else
		{
			if(code == KeyEvent.VK_DELETE) keyDelete();
			if(code == KeyEvent.VK_F1) keyF1();
			if(code == KeyEvent.VK_F2) keyF2();
			if(code == KeyEvent.VK_F3) keyF3();
			if(code == KeyEvent.VK_F4) keyF4();
			if(code == KeyEvent.VK_F5) keyF5();
		}
		
		table.repaint();
	}
	
	private void keyF1()		{send(this,"keyF1()");}
	private void keyF2()		{send(this,"keyF2()");}
	private void keyF3()		{send(this,"keyF3()");}
	private void keyF4()		{send(this,"keyF4()");}
	private void keyF5()		{send(this,"keyF5()");}
	
	private void keyCtrlX()		{send(this,"keyCtrlX()");}
	private void keyCtrlDelete()	{send(this,"keyCtrlDelete()");}
	private void keyCtrlF1()	{send(this,"keyCtrlF1()");}
	private void keyCtrlF2()	{send(this,"keyCtrlF2()");}
	private void keyCtrlF3()	{send(this,"keyCtrlF3()");}
	private void keyCtrlF4()	{send(this,"keyCtrlF4()");}
	private void keyCtrlF5()	{send(this,"keyCtrlF5()");}
	
	private void keyAltC()		{send(this,"keyAltC()");}
	private void keyAltV()		{send(this,"keyAltV()");}
	private void keyAltX()		{send(this,"keyAltX()");}
	private void keyAltDelete()	{send(this,"keyAltDelete()");}
	private void keyAltF1()		{send(this,"keyAltF1()");}
	private void keyAltF2()		{send(this,"keyAltF2()");}
	private void keyAltF3()		{send(this,"keyAltF3()");}
	private void keyAltF4()		{send(this,"keyAltF4()");}
	private void keyAltF5()		{send(this,"keyAltF5()");}
	
	
	private void keyDelete()
	{
		try
		{
			int[] selectedRows = table.getSelectedRows();
			int[] selectedColumns = table.getSelectedColumns();
			
			if(selectedRows.length==0 || selectedColumns.length==0) return;
			
			if(selectedColumns[0]==0)
			{
				int r = JOptionPane.showConfirmDialog(null,"Are you sure to remove paths from all configs?");
				if(r!=JOptionPane.YES_OPTION) return;
				
				List pathsToDelete = new ArrayList();
				for(int selectedRow : selectedRows)
				{
					String path = pathAt(selectedRow);
					if(path!=null) pathsToDelete.add(path);
				}
				
				for(int i=0;i<pathsToDelete.size();i++)
				{
					String path = (String) pathsToDelete.get(i);
					performRemovePath.f(path);
					
					paths.remove(path);
					pathsFiltered.remove(path);
					namesByPath.remove(path);
					pathsByName.forEach((key,value)->{((List)value).remove(path);});
				}
			}
			else
			{
				int nb = selectedColumns.length;
				int r = JOptionPane.showConfirmDialog(null,"Are you sure to remove paths from "+nb+" config(s)?");
				if(r!=JOptionPane.YES_OPTION) return;
				
				List pathsToDelete = new ArrayList();
				for(int selectedRow : selectedRows)
				{
					String path = pathAt(selectedRow);
					if(path!=null) pathsToDelete.add(path);
				}
				
				for(int selectedColumn : selectedColumns)
				{
					String name = nameAt(selectedColumn-1);
					for(int i=0;i<pathsToDelete.size();i++)
					{
						String path = (String) pathsToDelete.get(i);
						performRemoveNamePath.f(new Object[]{name, path});
						List namesForPath = (List) namesByPath.get(path);
						List pathsForName = (List) pathsByName.get(name);
						
						namesForPath.remove(name);
						pathsForName.remove(path);
						
						if(namesForPath.isEmpty()) 
						{
							namesByPath.remove(path);
							paths.remove(path);
							pathsFiltered.remove(path);
						}
					}
				}
			}
			refreshTable();
		}
		catch(Exception e)
		{Outside.err(this,"keyDelete()",e);}
	}
	
	
	private void keyCtrlC()
	{
		try
		{
			int[] selectedRows = table.getSelectedRows();
			int[] selectedColumns = table.getSelectedColumns();
			
			if(selectedRows.length==0 || selectedColumns.length==0) return;
			
			if(selectedColumns[0]==0)
			{
				List files = new ArrayList();
				for(int selectedRow : selectedRows)
				{
					String path = pathAt(selectedRow);
					if(path!=null) files.add(new File(path));
				}
				if(files.size()==1) clipboard.p(files.get(0));
				else clipboard.p(files);
			}
			else
			{
				List displays = new ArrayList();
				for(int selectedRow : selectedRows)
				{
					String path = pathAt(selectedRow);
					if(path!=null)
					{
						String display = pathDisplay(path);
						if(display!=null) displays.add(display);
						else displays.add(fileName(path));
					}
				}
				String allDisplays = (String) join.t(displays);
				clipboard.p(allDisplays);
			}
		}
		catch(Exception e)
		{Outside.err(this,"keyCtrlC()",e);}
	}
	
	
	private void keyCtrlV()
	{
		try
		{
			int[] selectedRows = table.getSelectedRows();
			int[] selectedColumns = table.getSelectedColumns();
			
			if(selectedRows.length==0 || selectedColumns.length==0) return;
			
			Object r = clipboard.g();
			if(r==null) return;
			
			if(r instanceof String)
			{
				initDisplayOnSelection((String) r);
				reload();
			}
			else if(r instanceof File)
			{
				addFileOnSelection((File) r);
				reload();
			}
			else if(r instanceof List)
			{
				List list = (List) r;
				addFileOnSelection((File) list.get(0));
				reload();
			}
		}
		catch(Exception e)
		{Outside.err(this,"keyCtrlV()",e);}
	}
	
	private void initDisplayOnSelection(String display) throws Exception
	{
		int[] selectedRows = table.getSelectedRows();
		if(selectedRows.length==0) return;
		
		for(int selectedRow : selectedRows)
		{
			String path = pathAt(selectedRow);
			if(path!=null)
			{
				labelCustManager.p(new Object[]{path, display});
			}
		}
	}
	
	private void addFileOnSelection(File file) throws Exception
	{
		int[] selectedColumns = table.getSelectedColumns();
		if(selectedColumns.length==0) return;
		
		for(int selectedColumn : selectedColumns) if(selectedColumn!=0)
		{
			String name = nameAt(selectedColumn-1);
			manager.v("addFile", new Object[]{name, file});
		}
	}
}