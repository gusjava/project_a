package a.entity.gus06.sys.entityeditor1.gui.gui1.list;

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

public class EntityImpl extends S1 implements Entity, I, V, G {

	public String creationDate() {return "20250925";}

	public static final String DISPLAY_CREATE = "ENTITY_add#Create entity [F1]";
	public static final String DISPLAY_DELETE = "ENTITY_remove#Delete entity [DEL]";
	public static final String DISPLAY_RENAME = "ENTITY_rename#Rename entity [F2]";
	public static final String DISPLAY_DUPLICATE = "ENTITY_duplicate#Duplicate entity [F3]";
	public static final String DISPLAY_PASTE = "ENTITY_paste#Paste entity [Ctrl-V]";
	public static final String DISPLAY_COPY = "ENTITY_copy#Copy entity [Ctrl-C]";

	public static final Color BG_SELECTED = new Color(244, 244, 244);
	public static final Color BG_UNSELECTED = Color.WHITE;
	
	public static final Color FG_VALID = Color.BLACK;
	public static final Color FG_COMPILE_ERR = Color.RED;
	public static final Color FG_XYZ_ERR = new Color(204,0,255); //violet
	public static final Color FG_MISSING_LINK = Color.ORANGE;
	public static final Color FG_SRC = Color.BLUE;
	
	
	
	private Service buildDataFull;
	private Service buildDataFiltered;
	private Service fieldHolder;
	private Service tableTooltip;
	private Service linkerListField;
	private Service toolbarFactory;
	private Service clearCopyPasteCut;
	private Service buildSelectionSup;
	private Service buildAction;
	private Service clipboard;
	private Service listToString;
	private Service listToStringHr;
	private Service stringToList;
	private Service autoScroll;
	
	private Service entityDelete;
	private Service entityDeleteAll;
	private Service entityCreate;
	private Service entityRename;
	private Service entityDuplicate;
	private Service performPaste;
	
	private Icon iconEntity;
	private Icon iconEntityLock;
	private Icon iconLocked;
	private Icon iconCompileErr;
	private Icon iconMissingLink;
	private Icon iconSrc;
	
	
	private JPanel panel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JComponent field;
	private JToolBar bar;
	
	private JLabel labelNumber;
	private JLabel labelNumberLocked;
	private JLabel labelNumberCompileErr;
	private JLabel labelNumberMissingLink;
	private JLabel labelNumberSrc;
	
	private Action actionCreate;
	private Action actionDelete;
	private Action actionRename;
	private Action actionDuplicate;
	private Action actionPaste;
	private Action actionCopy;

	private Object engine;
	private List dataFull = new ArrayList();
	private List dataFiltered = new ArrayList();
	private S1 selectionSup;
	
	
	public EntityImpl() throws Exception
	{
		buildDataFull = Outside.service(this, "gus06.sys.entityeditor1.gui.gui1.list.datafull");
		buildDataFiltered = Outside.service(this, "gus06.sys.entityeditor1.gui.gui1.list.datafiltered");
		fieldHolder = Outside.service(this, "*gus.data.editor.string.textfield.editor1");
		tableTooltip = Outside.service(this, "gus.x.swing.table.cust.tooltip1");
		linkerListField = Outside.service(this, "gus.x.swing.table.textfield.linker");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		clearCopyPasteCut = Outside.service(this, "gus.x.swing.comp.action.clear.copypastecut");
		buildSelectionSup = Outside.service(this, "gus06.x.swing.table.selection.buildsupport.multi");
		buildAction = Outside.service(this, "gus.y.swing1.action.builder1");
		clipboard = Outside.service(this,"gus06.x.clipboard.string");
		listToString = Outside.service(this,"gus06.x.list.string.join.n");
		listToStringHr = Outside.service(this,"gus06.x.list.string.join.hr");
		stringToList = Outside.service(this,"gus06.x.string.split.n.list");
		autoScroll = Outside.service(this,"gus06.swing.scroll.autoposition1");
		
		entityDelete = Outside.service(this,"gus06.y.entitysys1.perform.entity.delete.ask");
		entityDeleteAll = Outside.service(this,"gus06.y.entitysys1.perform.entity.deleteall.ask");
		entityCreate = Outside.service(this,"gus06.y.entitysys1.perform.entity.create.ask");
		entityRename = Outside.service(this,"gus06.y.entitysys1.perform.entity.rename.ask");
		entityDuplicate = Outside.service(this,"gus06.y.entitysys1.perform.entity.duplicate.ask");
		performPaste = Outside.service(this,"gus.y.entitysys1.perform.paste");
		
		iconEntity = (Icon) Outside.resource(this, "icon#ENTITY");
		iconEntityLock = (Icon) Outside.resource(this, "icon#ENTITY_lock");
		iconLocked = (Icon) Outside.resource(this, "icon#UTIL_lockR");
		iconCompileErr = (Icon) Outside.resource(this, "icon#UTIL_compileErr");
		iconMissingLink = (Icon) Outside.resource(this, "icon#UTIL_missingLink");
		iconSrc = (Icon) Outside.resource(this, "icon#UTIL_save");

		actionCreate = (Action) buildAction.t(new Object[] { DISPLAY_CREATE, (E) this::f1_entityCreate });
		actionDelete = (Action) buildAction.t(new Object[] { DISPLAY_DELETE, (E) this::del_entityDelete });
		actionRename = (Action) buildAction.t(new Object[] { DISPLAY_RENAME, (E) this::f2_entityRename });
		actionDuplicate = (Action) buildAction.t(new Object[] { DISPLAY_DUPLICATE, (E) this::f3_entityDuplicate });
		actionPaste = (Action) buildAction.t(new Object[] { DISPLAY_PASTE, (E) this::ctrl_shift_v_pasteSrc });
		actionCopy = (Action) buildAction.t(new Object[] { DISPLAY_COPY, (E) this::ctrl_shift_c_copySrc });
		
		labelNumber = new JLabel(" ");
		labelNumberLocked = new JLabel(" ");
		
		labelNumberCompileErr = new JLabel(" ");
		labelNumberMissingLink = new JLabel(" ");
		labelNumberSrc = new JLabel(" ");
		
		labelNumberCompileErr.setForeground(FG_COMPILE_ERR);
		labelNumberMissingLink.setForeground(FG_MISSING_LINK);
		labelNumberSrc.setForeground(FG_SRC);
		
		labelNumberCompileErr.setToolTipText("Compile errors");
		labelNumberMissingLink.setToolTipText("Missing links");
		labelNumberSrc.setToolTipText("Pending saves");
		
		bar = (JToolBar) toolbarFactory.i();

		bar.add(actionPaste);
		bar.add(actionCopy);
		bar.addSeparator();
		bar.add(actionCreate);
		bar.add(actionDuplicate);
		bar.add(actionDelete);
		bar.add(actionRename);

		field = (JComponent) fieldHolder.i();
		field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (e.isControlDown()) {
					if (code == KeyEvent.VK_Q) ctrl_q_lockAll();
					else if (code == KeyEvent.VK_W) ctrl_w_unlockAll();
				} else {
					if (code == KeyEvent.VK_F1) f1_entityCreate();
					else if (code == KeyEvent.VK_F2) f2_entityRename();
					else if (code == KeyEvent.VK_F3) f3_entityDuplicate();
					else if (code == KeyEvent.VK_F5) f5_forceReload();
				}
			}
		});

		model = new TableModel0();

		table = new JTable(model);
		table.setGridColor(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setUI(null);
		table.setDefaultRenderer(String.class, new TableCellRenderer1());
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (e.isControlDown() && e.isShiftDown())
				{
					if (code == KeyEvent.VK_C) ctrl_shift_c_copySrc();
					if (code == KeyEvent.VK_V) ctrl_shift_v_pasteSrc();
				}
				else if (e.isControlDown())
				{
					if (code == KeyEvent.VK_Q) ctrl_q_lockSelected();
					else if (code == KeyEvent.VK_W) ctrl_w_unlockSelected();
					else if (code == KeyEvent.VK_C) ctrl_c_copySelection();
					else if (code == KeyEvent.VK_V) ctrl_v_pasteSelection();
				} else {
					if (code == KeyEvent.VK_DELETE) del_entityDelete();
					else if (code == KeyEvent.VK_F1) f1_entityCreate();
					else if (code == KeyEvent.VK_F2) f2_entityRename();
					else if (code == KeyEvent.VK_F3) f3_entityDuplicate();
					else if (code == KeyEvent.VK_F5) f5_forceReload();
				}
			}
		});

		clearCopyPasteCut.p(table);
		tableTooltip.p(table);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		autoScroll.p(scroll);

		JPanel bottomPanel1 = wc(labelNumberMissingLink, labelNumberCompileErr);
		JPanel bottomPanel2 = wc(labelNumberSrc, bottomPanel1);
		JPanel bottomPanel3 = wc(labelNumberLocked, bottomPanel2);
		JPanel bottomPanel = wce(labelNumber, bottomPanel3, bar);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		linkerListField.p(new Object[] { table, field });
		selectionSup = (S1) buildSelectionSup.t(table);

		selectionSup.addActionListener(e -> handleSelection());
		fieldHolder.addActionListener(e -> handleInputEdition());
		
		initColumnSize(1, 60);
		initColumnSize(2, 30);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine"))
		{initEngine(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	private void initEngine(Object engine) throws Exception
	{
		this.engine = engine;
		
		((S) engine).addActionListener(e -> handleEngineEvent(e.getActionCommand()));
		fieldHolder.p(search());
	}
	
	
	public Object g() throws Exception
	{return getSelectionNames();}
	
	
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
	{refresh(getSelectionNames());}


	private void refresh(List selection) throws Exception
	{
		String search = getSearch();
		((V) engine).v("search", search);
		
		dataFiltered = (List) buildDataFiltered.t(new Object[] { engine, dataFull, search });
		labelNumber.setText(" " + getFilteredNumber() + "  ");
		
		selectionSup.setActivated(false);
		model.fireTableDataChanged();
		setSelection(selection);
		selectionSup.setActivated(true);

		refreshActions();
		refreshLabelNumberLocked();
		refreshLabelNumberCompileErr();
		refreshLabelNumberMissingLink();
		refreshLabelNumberSrc();

		selectionChanged();
	}

	/*
	 * SELECT
	 */

	private void select() throws Exception
	{
		List selectedNames = selectedNames();
		refresh(selectedNames);
	}

	/*
	 * ACTIONS
	 */

	private void ctrl_q_lockSelected()
	{
		try
		{
			List list = getSelectionNames();
			lock(list);
		}
		catch (Exception e)
		{Outside.err(this, "ctrl_q_lockSelected()", e);}
	}

	private void ctrl_w_unlockSelected()
	{
		try
		{
			List list = getSelectionNames();
			unlock(list);
		}
		catch (Exception e)
		{Outside.err(this, "ctrl_q_lockSelected()", e);}
	}

	private void ctrl_q_lockAll()
	{
		try
		{
			List list = getFilteredNames();
			lock(list);
		}
		catch (Exception e)
		{Outside.err(this, "ctrl_q_lockAll()", e);}
	}

	private void ctrl_w_unlockAll()
	{
		try
		{
			List list = getFilteredNames();
			unlock(list);
		}
		catch (Exception e)
		{Outside.err(this, "ctrl_w_unlockAll()", e);}
	}

	private void ctrl_c_copySelection()
	{
		try
		{
			List list = getSelectionNames();
			String s = (String) listToString.t(list);
			clipboard.p(s);
		}
		catch (Exception e)
		{Outside.err(this, "ctrl_c_copySelection()", e);}
	}

	private void ctrl_v_pasteSelection()
	{
		try
		{
			performPaste.p(new Object[] { engine, table });
		}
		catch (Exception e)
		{Outside.err(this, "ctrl_v_pasteSelection()", e);}
	}
	
	private void ctrl_shift_c_copySrc()
	{
		try
		{
			List list = getSelectionSrc();
			String s = (String) listToStringHr.t(list);
			clipboard.p(s);
		}
		catch (Exception e)
		{Outside.err(this, "ctrl_shift_c_copySrc()", e);}
	}
	
	private void ctrl_shift_v_pasteSrc()
	{
		try
		{
			performPaste.p(new Object[] { engine, table });
		}
		catch (Exception e)
		{Outside.err(this, "ctrl_shift_v_pasteSrc()", e);}
	}

	private void del_entityDelete()
	{
		try
		{
			List entityNames = getSelectionNames();
			if (canDeleteEntities(entityNames))
			entityDeleteAll.p(new Object[] { engine, entityNames, table });
		}
		catch (Exception e)
		{Outside.err(this, "del_entityDelete()", e);}
	}

	private void f1_entityCreate()
	{
		try
		{
			if(!canCreateEntity()) return;
			
			String initValue = getSelectionName();
			if(initValue==null) initValue = search();
			entityCreate.p(new Object[] { engine, table, initValue });
		}
		catch (Exception e)
		{Outside.err(this, "f1_entityCreate()", e);}
	}

	private void f2_entityRename()
	{
		try
		{
			String entityName = getSelectionName();
			if (canRenameEntity(entityName))
			entityRename.p(new Object[] { engine, entityName, table });
		}
		catch (Exception e)
		{Outside.err(this, "f2_entityRename()", e);}
	}

	private void f3_entityDuplicate()
	{
		try
		{
			String entityName = getSelectionName();
			if (canDuplicateEntity(entityName))
			entityDuplicate.p(new Object[] { engine, entityName, table });
		}
		catch (Exception e)
		{Outside.err(this, "f3_entityDuplicate()", e);}
	}

	private void f5_forceReload()
	{
		try
		{
			((E) engine).e();
		}
		catch (Exception e)
		{Outside.err(this, "f5_forceReload()", e);}
	}


	/*
	 * ENGINE DATA ACCESS
	 */

	private Map compileErrMap() throws Exception
	{return (Map) ((R)engine).r("compileErrMap");}

	private Map missingLinkMap() throws Exception
	{return (Map) ((R)engine).r("missingLinkMap");}

	private Map srcMap() throws Exception
	{return (Map) ((R)engine).r("srcMap");}

	private Set lockSet() throws Exception
	{return (Set) ((R)engine).r("lockSet");}

	private Set ignore1() throws Exception
	{return (Set) ((R)engine).r("ignore1");}

	private String search() throws Exception
	{return (String) ((R)engine).r("search");}
	
	private List selectedNames() throws Exception
	{return (List) ((R)engine).r("selectedNames");}


	/*
	 * ENGINE CONTROLS
	 */

	private void lock(List list) throws Exception
	{
		((V) engine).v("lock", list);
		table.repaint();
	}

	private void unlock(List list) throws Exception
	{
		((V) engine).v("unlock", list);
		table.repaint();
	}

	/*
	 * HANDLE EVENTS
	 */

	private void handleInputEdition()
	{
		try{refresh();}
		catch (Exception e)
		{Outside.err(this, "handleInputEdition()", e);}
	}
	
	public void handleSelection()
	{
		try
		{
			refreshActions();
			selectionChanged();
		}
		catch (Exception e)
		{Outside.err(this, "handleSelection()", e);}
	}
	
	private void handleEngineEvent(String s)
	{
		try
		{
			if (s.equals("locked()")) refresh();
			else if (s.equals("unlocked()")) refresh();
			
			else if (s.equals("srcSaved()")) refresh();
			else if (s.equals("srcCleared()")) refresh();
			
			else if (s.equals("selected()")) select();
			
			else if (s.equals("loaded()")) rebuild();
		}
		catch(Exception e)
		{Outside.err(this,"handleEngineEvent(String)",e);}
	}
	
	/*
	 * TABLE MODEL
	 */

	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 3;}
		public int getRowCount() {return getFilteredNumber();}
		public Class getColumnClass(int y) {return String.class;}

		public String getColumnName(int y)
		{
			if (y == 0) return "Entity name";
			if (y == 1) return "Features";
			if (y == 2) return "N";
			return "";
		}

		public Object getValueAt(int x, int y)
		{
			if (dataFiltered == null) return null;
			if (dataFiltered.size() <= x) return null;
			String[] infos = (String[]) dataFiltered.get(x);
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
				
			String s = (String) value;
			
			setBackground(getEntityBackground(isSelected));
			setIcon(column == 0 ? getEntityIcon(s) : null);
			String entityName = (String) table.getValueAt(row, 0);
			
			List srcList = getSrcList(entityName);
			if(srcList!=null)
			{
				setForeground(FG_SRC);
				setText(column == 0 ? s + " (" + srcList.size() + ")" : " "+s);
				return this;
			}
			
			List compileErrList = getCompileErrList(entityName);
			if(compileErrList!=null)
			{
				setForeground(FG_COMPILE_ERR);
				setText(column == 0 ? s + " (" + compileErrList.size() + ")" : " "+s);
				return this;
			}
			
			List missingLinkList = getMissingLinkList(entityName);
			if(missingLinkList!=null)
			{
				setForeground(FG_MISSING_LINK);
				setText(column == 0 ? s + " (" + missingLinkList.size() + ")" : " "+s);
				return this;
			}
			
			setForeground(FG_VALID);
			setText(column == 0 ? s : " "+s);
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

	private String getNameAt(int row)
	{return (String) table.getValueAt(row, 0);}

	private String getSrcAt(int row) throws Exception
	{
		String name = getNameAt(row);
		if(name==null) return null;
		return (String) ((R)engine).r("src_"+name);
	}

	/*
	 * SELECTION NAME
	 */

	private String getSelectionName()
	{
		if (table.getSelectionModel().isSelectionEmpty()) return null;
		int row = table.getSelectedRow();
		return getNameAt(row);
	}
	
	/*
	 * SELECTION NAMES
	 */
	
	private List getSelectionNames()
	{
		int[] rows = table.getSelectedRows();
		List list = new ArrayList();
		for (int row : rows) list.add(getNameAt(row));
		return list;
	}
	
	/*
	 * SELECTION SRC
	 */
	
	private List getSelectionSrc() throws Exception
	{
		int[] rows = table.getSelectedRows();
		List list = new ArrayList();
		for (int row : rows) list.add(getSrcAt(row));
		return list;
	}
	
	/*
	* SEARCH
	*/

	private String getSearch() throws Exception
	{return (String) fieldHolder.g();}


	private void setSelection(List entityNames)
	{
		table.clearSelection();
		if (entityNames == null || entityNames.isEmpty()) return;
		
		for (int i = 0; i < model.getRowCount(); i++)
		{
			String row = (String) model.getValueAt(i, 0);
			if (entityNames.contains(row))
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
	 * FILTERED NAMES
	 */
	
	private List getFilteredNames()
	{
		List list = new ArrayList();
		for (int i = 0; i < table.getRowCount(); i++) list.add(getNameAt(i));
		return list;
	}

	/*
	 * ERROR
	 */
	
	private List getCompileErrList(String entityName)
	{
		try
		{
			Map m = compileErrMap();
			if (m == null || !m.containsKey(entityName)) return null;
			return (List) m.get(entityName);
		}
		catch (Exception e)
		{Outside.err(this, "getCompileErrList(String)", e);}
		return null;
	}
	
	private List getMissingLinkList(String entityName)
	{
		try
		{
			Map m = missingLinkMap();
			if (m == null || !m.containsKey(entityName)) return null;
			return (List) m.get(entityName);
		}
		catch (Exception e)
		{Outside.err(this, "getMissingLinkList(String)", e);}
		return null;
	}
	
	private List getSrcList(String entityName)
	{
		try
		{
			Map m = srcMap();
			if (m == null || !m.containsKey(entityName)) return null;
			return (List) m.get(entityName);
		}
		catch (Exception e)
		{Outside.err(this, "getSrcSaveList(String)", e);}
		return null;
	}

	private Color getEntityBackground(boolean isSelected)
	{return isSelected ? BG_SELECTED : BG_UNSELECTED;}

	private Icon getEntityIcon(String entityName)
	{return isLocked(entityName) ? iconEntityLock : iconEntity;}

	private JPanel wc(JComponent w, JComponent c)
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(w, BorderLayout.WEST);
		panel.add(c, BorderLayout.CENTER);
		return panel;
	}

	private JPanel wce(JComponent w, JComponent c, JComponent e)
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(w, BorderLayout.WEST);
		panel.add(c, BorderLayout.CENTER);
		panel.add(e, BorderLayout.EAST);
		return panel;
	}

	/*
	 * LOCKED
	 */

	private boolean isLocked(String entityName)
	{
		try
		{
			Set lockSet = lockSet();
			return lockSet != null && lockSet.contains(entityName);
		}
		catch (Exception e)
		{Outside.err(this, "isLocked(String)", e);}
		return false;
	}

	/*
	 * REFRESH ACTIONS
	 */

	private void refreshLabelNumberLocked() throws Exception
	{
		Set lockSet = lockSet();
		if (lockSet == null || lockSet.isEmpty())
		{
			labelNumberLocked.setIcon(null);
			labelNumberLocked.setText(" ");
		}
		else
		{
			labelNumberLocked.setIcon(iconLocked);
			labelNumberLocked.setText(lockSet.size()+" ");
		}
	}

	private void refreshLabelNumberCompileErr() throws Exception
	{
		Map m = compileErrMap();
		if (m == null || m.isEmpty())
		{
			labelNumberCompileErr.setIcon(null);
			labelNumberCompileErr.setText(" ");
		}
		else
		{
			labelNumberCompileErr.setIcon(iconCompileErr);
			labelNumberCompileErr.setText(m.size()+" ");
		}
	}

	private void refreshLabelNumberMissingLink() throws Exception
	{
		Map m = missingLinkMap();
		if (m == null || m.isEmpty())
		{
			labelNumberMissingLink.setIcon(null);
			labelNumberMissingLink.setText(" ");
		}
		else
		{
			labelNumberMissingLink.setIcon(iconMissingLink);
			labelNumberMissingLink.setText(m.size()+" ");
		}
	}

	private void refreshLabelNumberSrc() throws Exception
	{
		Map m = srcMap();
		if (m == null || m.isEmpty())
		{
			labelNumberSrc.setIcon(null);
			labelNumberSrc.setText(" ");
		}
		else
		{
			labelNumberSrc.setIcon(iconSrc);
			labelNumberSrc.setText(m.size()+" ");
		}
	}

	private void refreshActions() throws Exception
	{
		String entityName = getSelectionName();
		List entityNames = getSelectionNames();
		
		boolean canCreate = canCreateEntity();
		boolean canDelete = canDeleteEntity(entityName);
		boolean canRename = canRenameEntity(entityName);
		boolean canDuplicate = canDuplicateEntity(entityName);
		
		actionCreate.setEnabled(canCreate);
		actionDelete.setEnabled(canDelete);
		actionRename.setEnabled(canRename);
		actionDuplicate.setEnabled(canDuplicate);
	}

	/*
	 * PERMISSIONS
	 */

	private boolean canCreateEntity() throws Exception
	{return ((F) engine).f(new Object[] { "canCreateEntity", null });}

	private boolean canDeleteEntity(String entityName) throws Exception
	{return ((F) engine).f(new Object[] { "canDeleteEntity", entityName });}

	private boolean canDeleteEntities(List entityNames) throws Exception
	{return ((F) engine).f(new Object[] { "canDeleteEntities", entityNames });}

	private boolean canRenameEntity(String entityName) throws Exception
	{return ((F) engine).f(new Object[] { "canRenameEntity", entityName });}

	private boolean canDuplicateEntity(String entityName) throws Exception
	{return ((F) engine).f(new Object[] { "canDuplicateEntity", entityName });}

	/*
	 * EVENTS
	 */

	private void selectionChanged()
	{send(this, "selectionChanged()");}
}
