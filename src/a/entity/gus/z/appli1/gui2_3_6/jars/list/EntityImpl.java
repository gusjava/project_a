package a.entity.gus.z.appli1.gui2_3_6.jars.list;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.table.AbstractTableModel;
import javax.swing.ListSelectionModel;
import java.util.Objects;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.util.Map;
import java.util.Set;

public class EntityImpl extends S1 implements Entity, I, G {

	public String creationDate() {return "20260421";}

	public static final Color BG_SELECTED = new Color(244, 244, 244);
	public static final Color BG_UNSELECTED = Color.WHITE;
	
	private Service engine;
	private Service buildDataFull;
	private Service buildDataFiltered;
	private Service fieldHolder;
	private Service tableTooltip;
	private Service linkerListField;
	private Service clearCopyPasteCut;
	private Service buildSelectionSup;
	
	private Service autoScroll;
	
	private Icon icon;
	
	private JPanel panel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JComponent field;
	
	private JLabel labelNumber;

	private List dataFull = new ArrayList();
	private List dataFiltered = new ArrayList();
	private S1 selectionSup;
	
	
	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus.y.entitysys1.engine");
		buildDataFull = Outside.service(this,"gus06.sys.entityeditor1.gui.gui3.list.datafull");
		buildDataFiltered = Outside.service(this,"gus06.sys.entityeditor1.gui.gui3.list.datafiltered");
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		tableTooltip = Outside.service(this,"gus.x.swing.table.cust.tooltip1");
		linkerListField = Outside.service(this,"gus.x.swing.table.textfield.linker");
		clearCopyPasteCut = Outside.service(this,"gus.x.swing.comp.action.clear.copypastecut");
		buildSelectionSup = Outside.service(this,"gus06.x.swing.table.selection.buildsupport.multi");
		autoScroll = Outside.service(this,"gus.x.swing.scroll.autoposition1");
		
		icon = (Icon) Outside.resource(this,"icon#FILE_jar");
		
		labelNumber = new JLabel(" ");

		field = (JComponent) fieldHolder.i();
		field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_F5) f5_forceReload();
			}
		});

		model = new TableModel0();
		
		TableCellRenderer1 renderer = new TableCellRenderer1();

		table = new JTable(model);
		table.setGridColor(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setUI(null);
		table.setDefaultRenderer(String.class, renderer);
		table.setDefaultRenderer(Long.class, renderer);
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_F5) f5_forceReload();
			}
		});

//		clearCopyPasteCut.p(table);
		tableTooltip.p(table);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		autoScroll.p(scroll);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(labelNumber, BorderLayout.SOUTH);

		linkerListField.p(new Object[] { table, field });
		selectionSup = (S1) buildSelectionSup.t(table);

		selectionSup.addActionListener(e -> handleSelection());
		fieldHolder.addActionListener(e -> handleInputEdition());
		
		initColumnSize(0, 0);
		initColumnSize(3, 30);
		
		engine.addActionListener(e -> handleEngineEvent(e.getActionCommand()));
		rebuild();
	}
	
	public Object g() throws Exception
	{return getSelection();}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private void rebuild()
	{
		try
		{
			dataFull = (List) buildDataFull.t(engine);
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"rebuild()",e);}
	}
	

	/*
	 * REFRESH
	 */
	

	private void refresh() throws Exception
	{
		String search = getSearch();
		List selection = getSelection();
		
		dataFiltered = (List) buildDataFiltered.t(new Object[] { engine, dataFull, search });
		labelNumber.setText(" " + getFilteredNumber() + "  ");
		
		selectionSup.setActivated(false);
		model.fireTableDataChanged();
		setSelection(selection);
		selectionSup.setActivated(true);

		selectionChanged();
	}

	/*
	 * ACTIONS
	 */

	private void f5_forceReload()
	{
		try
		{
			((E) engine).e();
		}
		catch (Exception e)
		{Outside.err(this,"f5_forceReload()", e);}
	}


	/*
	 * HANDLE EVENTS
	 */

	private void handleInputEdition()
	{
		try{refresh();}
		catch (Exception e)
		{Outside.err(this,"handleInputEdition()", e);}
	}
	
	public void handleSelection()
	{
		try
		{
			selectionChanged();
		}
		catch (Exception e)
		{Outside.err(this,"handleSelection()", e);}
	}
	
	private void handleEngineEvent(String s)
	{
		try
		{
			if (s.equals("loaded()")) rebuild();
		}
		catch(Exception e)
		{Outside.err(this,"handleEngineEvent(String)",e);}
	}
	
	/*
	 * TABLE MODEL
	 */

	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 4;}
		public int getRowCount() {return getFilteredNumber();}
		
		public Class getColumnClass(int y)
		{
			if (y == 0) return String.class;
			if (y == 1) return String.class;
			if (y == 2) return String.class;
			if (y == 3) return Long.class;
			return Object.class;
		}

		public String getColumnName(int y)
		{
			if (y == 0) return "";
			if (y == 1) return "Jar name";
			if (y == 2) return "Maven id";
			if (y == 3) return "Nb";
			return "";
		}

		public Object getValueAt(int x, int y)
		{
			if (dataFiltered == null) return null;
			if (dataFiltered.size() <= x) return null;
			Object[] infos = (Object[]) dataFiltered.get(x);
			return infos[y];
		}
	}

	/*
	 * TABLE RENDRER
	 */

	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, 
			Object value, 
			boolean isSelected, 
			boolean hasFocus,
			int row, 
			int column) {
			
			setBackground(isSelected ? BG_SELECTED : BG_UNSELECTED);
			setText(value!=null ? ""+value : "");
			setIcon(column==1 ? icon : null);
			
			return this;
		}
	}

	private int getFilteredNumber()
	{return dataFiltered != null ? dataFiltered.size() : 0;}

	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}

	private String getKeyAt(int row)
	{return (String) table.getValueAt(row, 0);}
	
	/*
	 * SELECTION
	 */
	
	private List getSelection()
	{
		int[] rows = table.getSelectedRows();
		List list = new ArrayList();
		for (int row : rows) list.add(getKeyAt(row));
		return list;
	}
	
	/*
	 * SEARCH
	 */

	private String getSearch() throws Exception
	{return (String) fieldHolder.g();}


	private void setSelection(List selection)
	{
		table.clearSelection();
		if (selection == null || selection.isEmpty()) return;
		
		for (int i = 0; i < table.getRowCount(); i++)
		{
			String key = getKeyAt(i);
			if (selection.contains(key))
			table.getSelectionModel().addSelectionInterval(i, i);
		}
		ensureRowIsVisible(0);
	}

	private void ensureRowIsVisible(int row)
	{
		Rectangle rect = table.getCellRect(row, 0, true);
		table.scrollRectToVisible(rect);
	}

	/*
	 * EVENTS
	 */

	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
