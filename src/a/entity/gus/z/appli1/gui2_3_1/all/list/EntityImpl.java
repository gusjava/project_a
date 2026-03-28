package a.entity.gus.z.appli1.gui2_3_1.all.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, I {
	public String creationDate() {return "20240112";}

	public static final String DISPLAY_CREATE = "ELEMENT_entity_add#Create entity [F1]";
	public static final String DISPLAY_DELETE = "ELEMENT_entity_delete#Delete entity [DEL]";
	public static final String DISPLAY_RENAME = "ELEMENT_entity_rename#Rename entity [F2]";
	public static final String DISPLAY_DUPLICATE = "ELEMENT_entity_duplicate#Duplicate entity [F3]";

	public static final Color BG_SELECTED = new Color(244, 244, 244);
	public static final Color BG_UNSELECTED = Color.WHITE;
	
	public static final Color FG_VALID = Color.BLACK;
	public static final Color FG_COMPILE_ERR = Color.RED;
	public static final Color FG_XYZ_ERR = new Color(204,0,255); //violet
	public static final Color FG_MISSING_LINK = Color.ORANGE;
	public static final Color FG_SRC_SAVE = Color.BLUE;

	// z
	private Service engine;
	private Service buildDataFull;
	private Service buildDataFiltered;
	
	// y
	private Service buildAction;
	private Service fieldHolder;
	private Service entityDelete;
	private Service entityCreate;
	private Service entityRename;
	private Service entityDuplicate;
	private Service persistField;
	private Service persistSet;
	
	// x
	private Service tableTooltip;
	private Service linkerListField;
	private Service toolbarFactory;
	private Service clearCopyPasteCut;
	private Service buildSelectionSup;
	private Service clipboard;
	private Service listToString;
	private Service stringToList;
	
	// resources
	private Icon iconEntity;
	private Icon iconEntityLock;
	private Icon iconLocked;
	private Icon iconCompileErr;
	private Icon iconXyzErr;
	private Icon iconMissingLink;
	private Icon iconSrcSave;
	
	
	private JPanel panel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JComponent field;
	private JToolBar bar;
	
	private JLabel labelNumber;
	private JLabel labelNumberLocked;
	private JLabel labelNumberCompileErr;
	private JLabel labelNumberXyzErr;
	private JLabel labelNumberMissingLink;
	private JLabel labelNumberSrcSave;


	private Action actionCreate;
	private Action actionDelete;
	private Action actionRename;
	private Action actionDuplicate;

	private List dataFull = new ArrayList();
	private List dataFiltered = new ArrayList();
	private S1 selectionSup;

	public EntityImpl() throws Exception {
		//z
		engine = Outside.service(this, "gus.z.appli1.gui2_3_1.all.engine");
		buildDataFull = Outside.service(this, "gus.z.appli1.gui2_3_1.all.list.datafull");
		buildDataFiltered = Outside.service(this, "gus.z.appli1.gui2_3_1.all.list.datafiltered");
		
		// y
		buildAction = Outside.service(this, "gus.y.swing1.action.builder1");
		fieldHolder = Outside.service(this, "*gus.y.swing1.textfield.editor1");
		entityDelete = Outside.service(this,"gus.y.entitysys1.perform.entity.delete.ask");
		entityCreate = Outside.service(this,"gus.y.entitysys1.perform.entity.create.ask");
		entityRename = Outside.service(this,"gus.y.entitysys1.perform.entity.rename.ask");
		entityDuplicate = Outside.service(this,"gus.y.entitysys1.perform.entity.duplicate.ask");
		persistField = Outside.service(this, "gus.y.persist1.swing.textcomp");
		persistSet = Outside.service(this, "gus.y.persist1.set.string");
		
		// x
		tableTooltip = Outside.service(this, "gus.x.swing.table.cust.tooltip1");
		linkerListField = Outside.service(this, "gus.x.swing.table.textfield.linker");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		clearCopyPasteCut = Outside.service(this, "gus.x.swing.comp.action.clear.copypastecut");
		buildSelectionSup = Outside.service(this, "gus.x.swing.table.selection.buildsupport");
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		listToString = Outside.service(this,"gus.x.list.join.n.string");
		stringToList = Outside.service(this,"gus.x.string.split.n.list");
		
		// resources
		iconEntity = (Icon) Outside.resource(this, "icon#ELEMENT_entity");
		iconEntityLock = (Icon) Outside.resource(this, "icon#ELEMENT_entity_lock");
		iconLocked = (Icon) Outside.resource(this, "icon#UTIL_lockR");
		iconCompileErr = (Icon) Outside.resource(this, "icon#UTIL_compileErr");
		iconXyzErr = (Icon) Outside.resource(this, "icon#UTIL_xyzErr");
		iconMissingLink = (Icon) Outside.resource(this, "icon#UTIL_missingLink");
		iconSrcSave = (Icon) Outside.resource(this, "icon#UTIL_saveSrc");

		actionCreate = (Action) buildAction.t(new Object[] { DISPLAY_CREATE, (E) this::f1_entityCreate });
		actionDelete = (Action) buildAction.t(new Object[] { DISPLAY_DELETE, (E) this::del_entityDelete });
		actionRename = (Action) buildAction.t(new Object[] { DISPLAY_RENAME, (E) this::f2_entityRename });
		actionDuplicate = (Action) buildAction.t(new Object[] { DISPLAY_DUPLICATE, (E) this::f3_entityDuplicate });

		labelNumber = new JLabel(" ");
		labelNumberLocked = new JLabel(" ");
		
		labelNumberCompileErr = new JLabel(" ");
		labelNumberXyzErr = new JLabel(" ");
		labelNumberMissingLink = new JLabel(" ");
		labelNumberSrcSave = new JLabel(" ");
		
		labelNumberCompileErr.setForeground(FG_COMPILE_ERR);
		labelNumberXyzErr.setForeground(FG_XYZ_ERR);
		labelNumberMissingLink.setForeground(FG_MISSING_LINK);
		labelNumberSrcSave.setForeground(FG_SRC_SAVE);
		
		labelNumberCompileErr.setToolTipText("Compile errors");
		labelNumberXyzErr.setToolTipText("XYZ errors");
		labelNumberMissingLink.setToolTipText("Missing links");
		labelNumberSrcSave.setToolTipText("Pending saves");

		bar = (JToolBar) toolbarFactory.i();

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
				if (e.isControlDown()) {
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

		JPanel bottomPanel1 = wc(labelNumberCompileErr, labelNumberXyzErr);
		JPanel bottomPanel2 = wc(labelNumberMissingLink, bottomPanel1);
		JPanel bottomPanel3 = wc(labelNumberSrcSave, bottomPanel2);
		JPanel bottomPanel4 = wc(labelNumberLocked, bottomPanel3);
		JPanel bottomPanel = wce(labelNumber, bottomPanel4, bar);

		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		initColumnSize(1, 70);
		initColumnSize(2, 20);

		linkerListField.p(new Object[] { table, field });
		selectionSup = (S1) buildSelectionSup.t(table);

		selectionSup.addActionListener(e -> handleSelection());
		fieldHolder.addActionListener(e -> handleInputEdition());
		engine.addActionListener(e -> handleEngineEvent(e.getActionCommand()));

		persistField.v(getClass().getName() + "_field", field);
		persistSet.v(getClass().getName() + "_lockSet", lockSet());
		rebuild();
	}
	
	/*
	 * FEATURES
	 */

	public Object g() throws Exception {
		return getSelection();
	}

	public Object i() throws Exception {
		return panel;
	}

	/*
	 * ENGINE DATA ACCESS
	 */

	private Map compileErrMap() throws Exception {
		return (Map) engine.r("compileErrMap");
	}

	private Map xyzErrMap() throws Exception {
		return (Map) engine.r("xyzErrMap");
	}

	private Map missingLinkMap() throws Exception {
		return (Map) engine.r("missingLinkMap");
	}

	private Map srcSaveMap() throws Exception {
		return (Map) engine.r("srcSaveMap");
	}

	private Set lockSet() throws Exception {
		return (Set) engine.r("lockSet");
	}

	/*
	 * ENGINE CONTROLS
	 */

	private void lock(List list) throws Exception {
		engine.v("lock", list);
		table.repaint();
	}

	private void unlock(List list) throws Exception {
		engine.v("unlock", list);
		table.repaint();
	}

	/*
	 * HANDLE EVENTS
	 */

	private void handleInputEdition() {
		try {
			refresh();
		} catch (Exception e) {
			Outside.err(this, "handleInputEdition()", e);
		}
	}
	
	public void handleSelection() {
		try {
			refreshActions();
			selectionChanged();
		} catch (Exception e) {
			Outside.err(this, "handleSelection()", e);
		}
	}

	private void handleEngineEvent(String s) {
		try {
			if (s.equals("locked()")) refresh();
			else if (s.equals("unlocked()")) refresh();
			
			else if (s.equals("srcSaved()")) refresh();
			else if (s.equals("srcCleared()")) refresh();
			
			else if (s.equals("selected()")) select();

			else if (s.equals("loaded()")) rebuild();
			else if (s.equals("entityAdded()")) handleEntityAdded();
			else if (s.equals("entityRenamed()")) handleEntityRenamed();
			else if (s.equals("entityDuplicated()")) handleEntityDuplicated();
			else if (s.equals("entityDeleted()")) handleEntityDeleted();
			else if (s.equals("entityModified()")) rebuild();
		} catch (Exception e) {
			Outside.err(this, "handleEngineEvent(String)", e);
		}
	}
	
	private void handleEntityAdded() throws Exception {
		String newName = (String) engine.r("info");
		rebuild(newName);
		selectionChanged();
	}
	
	private void handleEntityRenamed() throws Exception {
		String[] infos = (String[]) engine.r("info");
		rebuild(infos[1]);
		selectionChanged();
	}
	
	private void handleEntityDuplicated() throws Exception {
		String[] infos = (String[]) engine.r("info");
		rebuild(infos[1]);
		selectionChanged();
	}
	
	private void handleEntityDeleted() throws Exception {
		rebuild(null);
		selectionChanged();
	}

	/*
	 * ACTIONS
	 */

	private void ctrl_q_lockSelected() {
		try {
			List list = getSelectionNames();
			lock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_q_lockSelected()", e);
		}
	}

	private void ctrl_w_unlockSelected() {
		try {
			List list = getSelectionNames();
			unlock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_q_lockSelected()", e);
		}
	}

	private void ctrl_q_lockAll() {
		try {
			List list = getFilteredNames();
			lock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_q_lockAll()", e);
		}
	}

	private void ctrl_w_unlockAll() {
		try {
			List list = getFilteredNames();
			unlock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_w_unlockAll()", e);
		}
	}

	private void ctrl_c_copySelection() {
		try {
			List list = getSelectionNames();
			String s = (String) listToString.t(list);
			clipboard.p(s);
		} catch (Exception e) {
			Outside.err(this, "ctrl_c_copySelection()", e);
		}
	}

	private void ctrl_v_pasteSelection() {
		try {
			String s = (String) clipboard.g();
			List list = (List) stringToList.t(s);
			lock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_v_pasteSelection()", e);
		}
	}

	private void del_entityDelete() {
		try {
			String entityName = getSelection();
			if (canDeleteEntity(entityName))
				entityDelete.p(new Object[] { engine, entityName, table });
		} catch (Exception e) {
			Outside.err(this, "del_entityDelete()", e);
		}
	}

	private void f1_entityCreate() {
		try {
			if(canCreateEntity())
				entityCreate.p(new Object[] { engine, table });
		} catch (Exception e) {
			Outside.err(this, "f1_entityCreate()", e);
		}
	}

	private void f2_entityRename() {
		try {
			String entityName = getSelection();
			if (canRenameEntity(entityName))
				entityRename.p(new Object[] { engine, entityName, table });
		} catch (Exception e) {
			Outside.err(this, "f2_entityRename()", e);
		}
	}

	private void f3_entityDuplicate() {
		try {
			String entityName = getSelection();
			if (canDuplicateEntity(entityName))
				entityDuplicate.p(new Object[] { engine, entityName, table });
		} catch (Exception e) {
			Outside.err(this, "f3_entityDuplicate()", e);
		}
	}

	private void f5_forceReload() {
		try {
			engine.e();
		} catch (Exception e) {
			Outside.err(this, "f5_forceReload()", e);
		}
	}

	/*
	 * TABLE MODEL
	 */

	private class TableModel0 extends AbstractTableModel {
		public int getColumnCount() {
			return 3;
		}

		public int getRowCount() {
			return getFilteredNumber();
		}

		public String getColumnName(int y) {
			if (y == 0) return "Entity name";
			if (y == 1) return "Features";
			if (y == 2) return "N";
			return "";
		}

		public Class getColumnClass(int y) {
			return String.class;
		}

		public Object getValueAt(int x, int y) {
			if (dataFiltered == null) return null;
			if (dataFiltered.size() <= x) return null;
			String[] infos = (String[]) dataFiltered.get(x);
			return infos[y];
		}
	}

	/*
	 * TABLE RENDRER
	 */

	private class TableCellRenderer1 extends JLabel implements TableCellRenderer {
		public TableCellRenderer1() {
			super();
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			String s = (String) value;
			
			setBackground(getEntityBackground(isSelected));
			setIcon(column == 0 ? getEntityIcon(s) : null);
			String entityName = (String) table.getValueAt(row, 0);
			
			List srcSaveList = getSrcSaveList(entityName);
			if(srcSaveList!=null) {
				setForeground(FG_SRC_SAVE);
				setText(column == 0 ? s + " (" + srcSaveList.size() + ")" : " "+s);
				return this;
			}
			
			List compileErrList = getCompileErrList(entityName);
			if(compileErrList!=null) {
				setForeground(FG_COMPILE_ERR);
				setText(column == 0 ? s + " (" + compileErrList.size() + ")" : " "+s);
				return this;
			}
			
			List missingLinkList = getMissingLinkList(entityName);
			if(missingLinkList!=null) {
				setForeground(FG_MISSING_LINK);
				setText(column == 0 ? s + " (" + missingLinkList.size() + ")" : " "+s);
				return this;
			}
			
			List xyzErrList = getXyzErrList(entityName);
			if(xyzErrList!=null) {
				setForeground(FG_XYZ_ERR);
				setText(column == 0 ? s + " (" + xyzErrList.size() + ")" : " "+s);
				return this;
			}
			
			setForeground(FG_VALID);
			setText(column == 0 ? s : " "+s);
			return this;
		}
	}

	private String getSearch() throws Exception {
		return (String) fieldHolder.g();
	}

	private int getFilteredNumber() {
		return dataFiltered != null ? dataFiltered.size() : 0;
	}

	private void initColumnSize(int index, int size) {
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}

	private void rebuild() throws Exception {
		dataFull = (List) buildDataFull.t(engine);
		refresh();
	}
	
	private void rebuild(String newSelection) throws Exception {
		dataFull = (List) buildDataFull.t(engine);
		refresh(newSelection);
	}

	/*
	 * REFRESH
	 */

	private void refresh() throws Exception {
		refresh(getSelection());
	}

	private void refresh(String newSelection) throws Exception {
		dataFiltered = (List) buildDataFiltered.t(new Object[] { engine, dataFull, getSearch() });
		labelNumber.setText(" " + getFilteredNumber() + "  ");

		boolean selectionChange = !Objects.equals(getSelection(), newSelection);
		
		selectionSup.setActivated(false);
		model.fireTableDataChanged();
		setSelection(newSelection);
		selectionSup.setActivated(true);

		if(selectionChange) selectionChanged();

		refreshActions();
		refreshLabelNumberLocked();
		refreshLabelNumberCompileErr();
		refreshLabelNumberXyzErr();
		refreshLabelNumberMissingLink();
		refreshLabelNumberSrcSave();
	}

	/*
	 * SELECT
	 */

	private void select() throws Exception {
		String selectedName = (String) engine.r("selectedName");
		refresh(selectedName);
	}

	/*
	 * ERROR
	 */
	
	private List getCompileErrList(String entityName) {
		try {
			Map m = compileErrMap();
			if (m == null || !m.containsKey(entityName)) return null;
			return (List) m.get(entityName);
		} catch (Exception e) {
			Outside.err(this, "getCompileErrList(String)", e);
		}
		return null;
	}
	
	private List getXyzErrList(String entityName) {
		try {
			Map m = xyzErrMap();
			if (m == null || !m.containsKey(entityName)) return null;
			return (List) m.get(entityName);
		} catch (Exception e) {
			Outside.err(this, "getXyzErrList(String)", e);
		}
		return null;
	}
	
	private List getMissingLinkList(String entityName) {
		try {
			Map m = missingLinkMap();
			if (m == null || !m.containsKey(entityName)) return null;
			return (List) m.get(entityName);
		} catch (Exception e) {
			Outside.err(this, "getMissingLinkList(String)", e);
		}
		return null;
	}
	
	private List getSrcSaveList(String entityName) {
		try {
			Map m = srcSaveMap();
			if (m == null || !m.containsKey(entityName)) return null;
			return (List) m.get(entityName);
		} catch (Exception e) {
			Outside.err(this, "getSrcSaveList(String)", e);
		}
		return null;
	}

	private Color getEntityBackground(boolean isSelected) {
		return isSelected ? BG_SELECTED : BG_UNSELECTED;
	}

	private Icon getEntityIcon(String entityName) {
		return isLocked(entityName) ? iconEntityLock : iconEntity;
	}

	private JPanel wc(JComponent w, JComponent c) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(w, BorderLayout.WEST);
		panel.add(c, BorderLayout.CENTER);
		return panel;
	}

	private JPanel wce(JComponent w, JComponent c, JComponent e) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(w, BorderLayout.WEST);
		panel.add(c, BorderLayout.CENTER);
		panel.add(e, BorderLayout.EAST);
		return panel;
	}

	private String getNameAt(int row) {
		return (String) table.getValueAt(row, 0);
	}

	/*
	 * SELECTION
	 */

	private String getSelection() {
		if (table.getSelectionModel().isSelectionEmpty())
			return null;
		int row = table.getSelectedRow();
		return getNameAt(row);
	}

	private void setSelection(String entityName) {
		if (entityName == null) {
			table.clearSelection();
			return;
		}
		for (int i = 0; i < model.getRowCount(); i++)
			if (model.getValueAt(i, 0).equals(entityName)) {
				table.getSelectionModel().setSelectionInterval(i, i);
				ensureRowIsVisible(i);
				return;
			}
	}

	private void ensureRowIsVisible(int row) {
		Rectangle rect = table.getCellRect(row, 0, true);
		table.scrollRectToVisible(rect);
	}
	
	/*
	 * SELECTION NAMES
	 */
	
	private List getSelectionNames() {
		int[] rows = table.getSelectedRows();
		List list = new ArrayList();
		for (int row : rows)
			list.add(getNameAt(row));
		return list;
	}
	
	/*
	 * FILTERED NAMES
	 */
	
	private List getFilteredNames() {
		List list = new ArrayList();
		for (int i = 0; i < table.getRowCount(); i++)
			list.add(getNameAt(i));
		return list;
	}

	/*
	 * LOCKED
	 */

	private boolean isLocked(String entityName) {
		try {
			Set lockSet = lockSet();
			return lockSet != null && lockSet.contains(entityName);
		} catch (Exception e) {
			Outside.err(this, "isLocked(String)", e);
		}
		return false;
	}

	/*
	 * REFRESH ACTIONS
	 */

	private void refreshLabelNumberLocked() throws Exception {
		Set lockSet = lockSet();
		if (lockSet == null || lockSet.isEmpty()) {
			labelNumberLocked.setIcon(null);
			labelNumberLocked.setText(" ");
		} else {
			labelNumberLocked.setIcon(iconLocked);
			labelNumberLocked.setText(lockSet.size()+" ");
		}
	}

	private void refreshLabelNumberCompileErr() throws Exception {
		Map m = compileErrMap();
		if (m == null || m.isEmpty()) {
			labelNumberCompileErr.setIcon(null);
			labelNumberCompileErr.setText(" ");
		} else {
			labelNumberCompileErr.setIcon(iconCompileErr);
			labelNumberCompileErr.setText(m.size()+" ");
		}
	}

	private void refreshLabelNumberXyzErr() throws Exception {
		Map m = xyzErrMap();
		if (m == null || m.isEmpty()) {
			labelNumberXyzErr.setIcon(null);
			labelNumberXyzErr.setText(" ");
		} else {
			labelNumberXyzErr.setIcon(iconXyzErr);
			labelNumberXyzErr.setText(m.size()+" ");
		}
	}

	private void refreshLabelNumberMissingLink() throws Exception {
		Map m = missingLinkMap();
		if (m == null || m.isEmpty()) {
			labelNumberMissingLink.setIcon(null);
			labelNumberMissingLink.setText(" ");
		} else {
			labelNumberMissingLink.setIcon(iconMissingLink);
			labelNumberMissingLink.setText(m.size()+" ");
		}
	}

	private void refreshLabelNumberSrcSave() throws Exception {
		Map m = srcSaveMap();
		if (m == null || m.isEmpty()) {
			labelNumberSrcSave.setIcon(null);
			labelNumberSrcSave.setText(" ");
		} else {
			labelNumberSrcSave.setIcon(iconSrcSave);
			labelNumberSrcSave.setText(m.size()+" ");
		}
	}

	private void refreshActions() throws Exception {
		String entityName = getSelection();
		
		actionCreate.setEnabled(canCreateEntity());
		actionDelete.setEnabled(canDeleteEntity(entityName));
		actionRename.setEnabled(canRenameEntity(entityName));
		actionDuplicate.setEnabled(canDuplicateEntity(entityName));
	}

	/*
	 * PERMISSIONS
	 */

	private boolean canCreateEntity() throws Exception {
		return engine.f(new String[] { "canCreateEntity", null });
	}

	private boolean canDeleteEntity(String entityName) throws Exception {
		return engine.f(new String[] { "canDeleteEntity", entityName });
	}

	private boolean canRenameEntity(String entityName) throws Exception {
		return engine.f(new String[] { "canRenameEntity", entityName });
	}

	private boolean canDuplicateEntity(String entityName) throws Exception {
		return engine.f(new String[] { "canDuplicateEntity", entityName });
	}

	/*
	 * EVENTS
	 */

	private void selectionChanged() {
		send(this, "selectionChanged()");
	}
}
