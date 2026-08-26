package a.entity.gus.z.appli1.gui2_3_3.y.detail.entities.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, I, ListSelectionListener, ActionListener {
	public String creationDate() {return "20260826";}

	public static final String DISPLAY_CREATE = "ELEMENT_entity_add#Create entity [F1]";
	public static final String DISPLAY_DELETE = "ELEMENT_entity_delete#Delete entity [DEL]";
	public static final String DISPLAY_RENAME = "ELEMENT_entity_rename#Rename entity [F2]";
	public static final String DISPLAY_DUPLICATE = "ELEMENT_entity_duplicate#Duplicate entity [F3]";

	public static final Color BG_SELECTED = new Color(244, 244, 244);
	public static final Color BG_UNSELECTED = Color.WHITE;
	public static final Color FG_COMPILED = Color.BLACK;
	public static final Color FG_UNCOMPILED = Color.RED;

	private Service engine;
	private Service buildDataFull;
	private Service buildDataFiltered;

	private Service buildAction;
	private Service fieldHolder;
	private Service entityCreate;
	private Service entityDelete;
	private Service entityRename;
	private Service entityDuplicate;

	private Service tableTooltip;
	private Service linkerListField;
	private Service toolbarFactory;
	private Service clearCopyPasteCut;

	private Icon iconEntity;
	private Icon iconEntityLock;
	private Icon iconLock;
	private Icon iconErr;

	private JPanel panel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JComponent field;
	private JToolBar bar;

	private JLabel labelNumber;
	private JLabel labelNumberLocked;
	private JLabel labelNumberError;

	private Action actionCreate;
	private Action actionDelete;
	private Action actionRename;
	private Action actionDuplicate;

	private String yPrefix;
	private List dataFull = new ArrayList();
	private List dataFiltered = new ArrayList();

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail.entities.engine");
		buildDataFull = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail.entities.list.datafull");
		buildDataFiltered = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail.entities.list.datafiltered");

		buildAction = Outside.service(this, "gus.y.swing1.action.builder1");
		fieldHolder = Outside.service(this, "*gus.y.swing1.textfield.editor1");
		entityCreate = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail.entities.list.create.ask");
		entityDelete = Outside.service(this, "gus.y.entitysys1.perform.entity.delete.ask");
		entityRename = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail.entities.list.rename.ask");
		entityDuplicate = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail.entities.list.duplicate.ask");

		tableTooltip = Outside.service(this, "gus.x.swing.table.cust.tooltip1");
		linkerListField = Outside.service(this, "gus.x.swing.table.textfield.linker");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		clearCopyPasteCut = Outside.service(this, "gus.x.swing.comp.action.clear.copypastecut");

		iconEntity = (Icon) Outside.resource(this, "icon#ELEMENT_entity");
		iconEntityLock = (Icon) Outside.resource(this, "icon#ELEMENT_entity_lock");
		iconLock = (Icon) Outside.resource(this, "icon#UTIL_lockR");
		iconErr = (Icon) Outside.resource(this, "icon#UTIL_errorR");

		actionCreate = (Action) buildAction.t(new Object[] { DISPLAY_CREATE, (E) this::f1_entityCreate });
		actionDelete = (Action) buildAction.t(new Object[] { DISPLAY_DELETE, (E) this::del_entityDelete });
		actionRename = (Action) buildAction.t(new Object[] { DISPLAY_RENAME, (E) this::f2_entityRename });
		actionDuplicate = (Action) buildAction.t(new Object[] { DISPLAY_DUPLICATE, (E) this::f3_entityDuplicate });

		labelNumber = new JLabel(" ");
		labelNumberLocked = new JLabel(" ");
		labelNumberError = new JLabel(" ");

		bar = (JToolBar) toolbarFactory.i();

		bar.add(actionCreate);
		bar.add(actionDelete);
		bar.add(actionRename);
		bar.add(actionDuplicate);

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

		initColumnSize(1, 70);
		initColumnSize(2, 20);

		JPanel bottomPanel = wce(labelNumber, wc(labelNumberLocked, labelNumberError), bar);

		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		linkerListField.p(new Object[] { table, field });
		fieldHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleInputEdition();
			}
		});

		table.getSelectionModel().addListSelectionListener(this);
		engine.addActionListener(this);

		rebuild();
	}

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

	private Set errorSet() throws Exception {
		Map m = compileErrMap();
		return m != null ? m.keySet() : null;
	}

	private String devId() throws Exception {
		return (String) engine.r("devId");
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

	public void actionPerformed(ActionEvent e) {
		handleEngineEvent(e.getActionCommand());
	}

	private void handleEngineEvent(String s) {
		try {
			if (s.equals("locked()")) refresh();
			else if (s.equals("unlocked()")) refresh();
			else if (s.equals("srcSaved()")) refresh();
			else if (s.equals("srcCleared()")) refresh();

			else if (s.equals("scopeChanged()")) rebuild(null);

			else if (s.equals("loaded()")) rebuild();
			else if (s.equals("entityAdded()")) handleEntityAdded();
			else if (s.equals("entityRenamed()")) handleEntityRenamed();
			else if (s.equals("entityDuplicated()")) handleEntityDuplicated();
			else if (s.equals("entityReplaced()")) rebuild();
			else if (s.equals("entitiesReplaced()")) rebuild();
			else if (s.equals("entityDeleted()")) handleEntityDeleted();
			else if (s.equals("entitiesDeleted()")) handleEntitiesDeleted();
			else if (s.equals("entityModified()")) rebuild();
		} catch (Exception e) {
			Outside.err(this, "handleEngineEvent(String)", e);
		}
	}

	private void handleEntityAdded() throws Exception {
		String newName = (String) engine.r("info");
		rebuild(newName);
	}

	private void handleEntityRenamed() throws Exception {
		String[] infos = (String[]) engine.r("info");
		rebuild(infos[1]);
	}

	private void handleEntityDuplicated() throws Exception {
		String[] infos = (String[]) engine.r("info");
		rebuild(infos[1]);
	}

	private void handleEntityDeleted() throws Exception {
		rebuild(null);
	}

	private void handleEntitiesDeleted() throws Exception {
		rebuild(null);
	}

	/*
	 * ACTIONS
	 */

	private void ctrl_q_lockSelected() {
		try {
			lock(getSelectionNames());
		} catch (Exception e) {
			Outside.err(this, "ctrl_q_lockSelected()", e);
		}
	}

	private void ctrl_w_unlockSelected() {
		try {
			unlock(getSelectionNames());
		} catch (Exception e) {
			Outside.err(this, "ctrl_w_unlockSelected()", e);
		}
	}

	private void ctrl_q_lockAll() {
		try {
			lock(getFilteredNames());
		} catch (Exception e) {
			Outside.err(this, "ctrl_q_lockAll()", e);
		}
	}

	private void ctrl_w_unlockAll() {
		try {
			unlock(getFilteredNames());
		} catch (Exception e) {
			Outside.err(this, "ctrl_w_unlockAll()", e);
		}
	}

	private void del_entityDelete() {
		try {
			String entityName = getSelection();
			if (entityName != null)
				entityDelete.p(new Object[] { engine, entityName, table });
		} catch (Exception e) {
			Outside.err(this, "del_entityDelete()", e);
		}
	}

	private void f1_entityCreate() {
		try {
			if (yPrefix != null)
				entityCreate.p(new Object[] { engine, yPrefix, table });
		} catch (Exception e) {
			Outside.err(this, "f1_entityCreate()", e);
		}
	}

	private void f2_entityRename() {
		try {
			String entityName = getSelection();
			if (yPrefix != null && entityName != null)
				entityRename.p(new Object[] { engine, yPrefix, entityName, table });
		} catch (Exception e) {
			Outside.err(this, "f2_entityRename()", e);
		}
	}

	private void f3_entityDuplicate() {
		try {
			String entityName = getSelection();
			if (yPrefix != null && entityName != null)
				entityDuplicate.p(new Object[] { engine, yPrefix, entityName, table });
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
			if (dataFiltered == null || dataFiltered.size() <= x) return null;
			String[] infos = (String[]) dataFiltered.get(x);
			if (y == 0) return infos[1];
			if (y == 1) return infos[2];
			if (y == 2) return infos[3];
			return null;
		}
	}

	/*
	 * TABLE RENDERER
	 */

	private class TableCellRenderer1 extends JLabel implements TableCellRenderer {
		public TableCellRenderer1() {
			super();
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			String s = (String) value;
			String entityName = getNameAt(row);

			setBackground(getEntityBackground(isSelected));
			setIcon(column == 0 ? getEntityIcon(entityName) : null);

			int errNumber = getErrorNumber(entityName);
			if (errNumber > 0) {
				setForeground(FG_UNCOMPILED);
				setText(column == 0 ? s + " (" + errNumber + ")" : " " + s);
				return this;
			}

			setForeground(FG_COMPILED);
			setText(column == 0 ? s : " " + s);
			return this;
		}
	}

	private int getFilteredNumber() {
		return dataFiltered != null ? dataFiltered.size() : 0;
	}

	private void initColumnSize(int index, int size) {
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}

	private void rebuild() throws Exception {
		rebuild(getSelection());
	}

	private void rebuild(String newSelection) throws Exception {
		yPrefix = (String) engine.r("yPrefix");
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
		String currentSelection = getSelection();
		dataFiltered = (List) buildDataFiltered.t(new Object[] { engine, dataFull, getSearch() });
		labelNumber.setText(" " + getFilteredNumber() + "  ");

		boolean selectionChange = !Objects.equals(currentSelection, newSelection);

		table.getSelectionModel().removeListSelectionListener(this);
		model.fireTableDataChanged();
		setSelection(newSelection);
		table.getSelectionModel().addListSelectionListener(this);

		if (selectionChange) selectionChanged();

		refreshLabelNumberLocked();
		refreshLabelNumberError();
	}

	private String getSearch() throws Exception {
		return (String) fieldHolder.g();
	}

	public void valueChanged(ListSelectionEvent e) {
		selectionChanged();
	}

	/*
	 * SELECTION
	 */

	private String getNameAt(int row) {
		if (dataFiltered == null || dataFiltered.size() <= row) return null;
		String[] infos = (String[]) dataFiltered.get(row);
		return infos[0];
	}

	private String getSelection() {
		if (table.getSelectionModel().isSelectionEmpty()) return null;
		int row = table.getSelectedRow();
		return getNameAt(row);
	}

	private void setSelection(String entityName) {
		if (entityName == null) {
			table.clearSelection();
			return;
		}
		for (int i = 0; i < model.getRowCount(); i++)
			if (entityName.equals(getNameAt(i))) {
				table.getSelectionModel().setSelectionInterval(i, i);
				ensureRowIsVisible(i);
				return;
			}
		table.clearSelection();
	}

	private void ensureRowIsVisible(int row) {
		Rectangle rect = table.getCellRect(row, 0, true);
		table.scrollRectToVisible(rect);
	}

	private List getSelectionNames() {
		int[] rows = table.getSelectedRows();
		List list = new ArrayList();
		for (int row : rows)
			list.add(getNameAt(row));
		return list;
	}

	private List getFilteredNames() {
		List list = new ArrayList();
		for (int i = 0; i < table.getRowCount(); i++)
			list.add(getNameAt(i));
		return list;
	}

	/*
	 * ENTITY INFOS
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

	private int getErrorNumber(String entityName) {
		try {
			Map m = compileErrMap();
			if (m == null || !m.containsKey(entityName)) return 0;
			return ((List) m.get(entityName)).size();
		} catch (Exception e) {
			Outside.err(this, "getErrorNumber(String)", e);
		}
		return 0;
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

	/*
	 * REFRESH LABELS
	 */

	private void refreshLabelNumberLocked() throws Exception {
		Set lockSet = lockSet();
		if (lockSet == null || lockSet.isEmpty()) {
			labelNumberLocked.setIcon(null);
			labelNumberLocked.setText(" ");
		} else {
			labelNumberLocked.setIcon(iconLock);
			labelNumberLocked.setText(lockSet.size() + " ");
		}
	}

	private void refreshLabelNumberError() throws Exception {
		Map m = compileErrMap();
		if (m == null || m.isEmpty()) {
			labelNumberError.setIcon(null);
			labelNumberError.setText(" ");
		} else {
			labelNumberError.setIcon(iconErr);
			labelNumberError.setText(m.size() + " ");
		}
	}

	/*
	 * EVENTS
	 */

	private void selectionChanged() {
		send(this, "selectionChanged()");
	}
}
