package a.entity.gus.z.appli1.gui2_3_3.y.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import a.framework.E;
import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl extends S1 implements Entity, G, I, ListSelectionListener, ActionListener {
	public String creationDate() {return "20240113";}

	public static final String DISPLAY_CREATE = "ELEMENT_entity_add#Create entity [F1]";
	public static final String DISPLAY_DELETE = "ELEMENT_entity_delete#Delete entity [DEL]";
	public static final String DISPLAY_RENAME = "ELEMENT_entity_rename#Rename entity [F2]";
	public static final String DISPLAY_DUPLICATE = "ELEMENT_entity_duplicate#Duplicate entity [F3]";

	public static final Color BG_SELECTED = new Color(244, 244, 244);
	public static final Color BG_UNSELECTED = Color.WHITE;
	public static final Color FG_COMPILED = Color.BLACK;
	public static final Color FG_UNCOMPILED = Color.RED;

	private Service engine;
	private Service filterList;
	private Service fieldHolder;
	private Service tableTooltip;
	private Service linkerListField;
	private Service toolbarFactory;
	private Service buildAction;
	private Service clearCopyPasteCut;

	private JPanel panel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JComponent field;
	private JLabel labelNumber;
	private JLabel labelNumberLocked;
	private JLabel labelNumberError;
	private JToolBar bar;

	private Icon iconEntity;
	private Icon iconEntityLock;
	private Icon iconLock;
	private Icon iconErr;

	private Action actionCreate;
	private Action actionDelete;
	private Action actionRename;
	private Action actionDuplicate;

	private List dataFull = new ArrayList();
	private List dataFiltered = new ArrayList();

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.z.appli1.gui2_3_3.y.engine");
		filterList = Outside.service(this, "gus.z.appli1.gui2_3_3.y.filter");
		fieldHolder = Outside.service(this, "*gus.y.swing1.textfield.editor1");
		tableTooltip = Outside.service(this, "gus.x.swing.table.cust.tooltip1");
		linkerListField = Outside.service(this, "gus.x.swing.table.textfield.linker");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		buildAction = Outside.service(this, "gus.y.swing1.action.builder1");
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
					if (code == KeyEvent.VK_Q)
						ctrl_q_lockAll();
					else if (code == KeyEvent.VK_W)
						ctrl_w_unlockAll();
				} else {
					if (code == KeyEvent.VK_F1)
						f1_entityCreate();
					else if (code == KeyEvent.VK_F2)
						f2_entityRename();
					else if (code == KeyEvent.VK_F3)
						f3_entityDuplicate();
					else if (code == KeyEvent.VK_F5)
						f5_forceReload();
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
					if (code == KeyEvent.VK_Q)
						ctrl_q_lockSelected();
					else if (code == KeyEvent.VK_W)
						ctrl_w_unlockSelected();
					else if (code == KeyEvent.VK_C)
						ctrl_c_copySelection();
					else if (code == KeyEvent.VK_V)
						ctrl_v_pasteSelection();
				} else {
					if (code == KeyEvent.VK_DELETE)
						del_entityDelete();
					else if (code == KeyEvent.VK_F1)
						f1_entityCreate();
					else if (code == KeyEvent.VK_F2)
						f2_entityRename();
					else if (code == KeyEvent.VK_F3)
						f3_entityDuplicate();
					else if (code == KeyEvent.VK_F5)
						f5_forceReload();
				}
			}
		});

		clearCopyPasteCut.p(table);
		tableTooltip.p(table);

		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);

		JPanel bottomPanel = wce(labelNumber, wc(labelNumberLocked, labelNumberError), bar);

		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		initColumnSize(1, 40);

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

	private Map errorMap() throws Exception {
		return (Map) engine.r("errorMap");
	}

	private Set errorSet() throws Exception {
		Map m = errorMap();
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
	 * HANDLE INPUT EDITION
	 */

	private void handleInputEdition() {
		refreshLater();
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		handleEngineEvent(s);
	}

	private void handleEngineEvent(String s) {
		try {
			if(s.equals("lockAdded()")) 
				refreshLater();
			else if(s.equals("lockRemoved()"))
				refreshLater();
			
			else if(s.equals("loaded()"))
				rebuild();
			else if(s.equals("entityAdded()"))
				rebuild();
			else if(s.equals("entityRenamed()"))
				rebuild();
			else if(s.equals("entityDuplicated()"))
				rebuild();
			else if(s.equals("entityDeleted()"))
				rebuild();
			else if(s.equals("entityModified()"))
				rebuild();
		} catch (Exception e) {
			Outside.err(this, "handleEngineEvent(String)", e);
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		selectionChanged();
	}

	/*
	 * ACTIONS
	 */

	private void ctrl_q_lockSelected() {
		try {
			int[] rows = table.getSelectedRows();
			List list = new ArrayList();
			for (int row : rows)
				list.add(getNameAt(row));
			lock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_q_lockSelected()", e);
		}
	}

	private void ctrl_w_unlockSelected() {
		try {
			int[] rows = table.getSelectedRows();
			List list = new ArrayList();
			for (int row : rows)
				list.add(getNameAt(row));
			unlock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_q_lockSelected()", e);
		}
	}

	private void ctrl_q_lockAll() {
		try {
			List list = new ArrayList();
			for (int i = 0; i < table.getRowCount(); i++)
				list.add(getNameAt(i));
			lock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_q_lockAll()", e);
		}
	}

	private void ctrl_w_unlockAll() {
		try {
			List list = new ArrayList();
			for (int i = 0; i < table.getRowCount(); i++)
				list.add(getNameAt(i));
			unlock(list);
		} catch (Exception e) {
			Outside.err(this, "ctrl_w_unlockAll()", e);
		}
	}

	private void ctrl_c_copySelection() {
		try {
			StringBuffer b = new StringBuffer();
			int[] rows = table.getSelectedRows();
			for (int i = 0; i < rows.length; i++) {
				String name = (String) table.getValueAt(rows[i], 0);
				b.append(name);
				if (i < rows.length - 1)
					b.append("\n");
			}
//			clipboard.p(b.toString());
		} catch (Exception e) {
			Outside.err(this, "ctrl_c_copySelection()", e);
		}
	}

	private void ctrl_v_pasteSelection() {
		try {
//			String s = (String) clipboard.g();
//			String[] n = s.split("[\n\r]+");
//			Set set = new HashSet();
//			for (int i = 0; i < n.length; i++)
//				set.add(n[i]);
//			lock(set);
		} catch (Exception e) {
			Outside.err(this, "ctrl_v_pasteSelection()", e);
		}
	}

	private void del_entityDelete() {
		try {
//			if (!canDeleteSelected())
//				return;
//			if (engine1 != null)
//				entityDelete.p(new Object[] { engine1, getSelection(), table });
		} catch (Exception e) {
			Outside.err(this, "del_entityDelete()", e);
		}
	}

	private void f1_entityCreate() {
		try {
//			if (engine1 != null)
//				entityCreate.p(new Object[] { engine1, table });
		} catch (Exception e) {
			Outside.err(this, "f1_entityCreate()", e);
		}
	}

	private void f2_entityRename() {
		try {
//			if (!canRenameSelected())
//				return;
//			if (engine1 != null)
//				entityRename.p(new Object[] { engine1, getSelection(), table });
		} catch (Exception e) {
			Outside.err(this, "f2_entityRename()", e);
		}
	}

	private void f3_entityDuplicate() {
		try {
//			if (!canDuplicateSelected())
//				return;
//			if (engine1 != null)
//				entityDuplicate.p(new Object[] { engine1, getSelection(), table });
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
			return 2;
		}

		public int getRowCount() {
			return getFilteredNumber();
		}

		public String getColumnName(int y) {
			if (y == 0)
				return "System name";
			if (y == 1)
				return "Entities";
			return "";
		}

		public Class getColumnClass(int y) {
			return String.class;
		}

		public Object getValueAt(int x, int y) {
			if (dataFiltered == null)
				return null;
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

			String yName = (String) table.getValueAt(row, 0);
			int errNumber = getErrorNumber(yName);

			if (column == 0) {
				setIcon(getEntityIcon(s));
				setText(errNumber > 0 ? s + " (" + errNumber + ")" : s);
			} else {
				setIcon(null);
				setText(" " + s);
			}

			setBackground(getEntityBackground(isSelected));
			setForeground(getEntityForeground(yName));
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
		buildDataFull();
		refreshLater();
	}

	private void buildDataFull() throws Exception {
		dataFull = new ArrayList();

		List yNameList = (List) engine.r("yNameList");
		Map entitiesByYName = (Map) engine.r("entitiesByYName");
		
		int nb = yNameList.size();
		for (int i = 0; i < nb; i++) {
			String yName = (String) yNameList.get(i);
			List entities = (List) entitiesByYName.get(yName);
			int entityNb = entities.size();
			dataFull.add(new String[] { yName, ""+entityNb });
		}
	}

	/*
	 * REFRESH
	 */

	private void refreshLater() {
		SwingUtilities.invokeLater(() -> {
			table.getSelectionModel().removeListSelectionListener(this);
			refresh();
			table.getSelectionModel().addListSelectionListener(this);
		});
	}

	private void refresh() {
		try {
			updateTable();
//			selectEntity();
//			refreshLabelNumberLocked();
//			refreshActions();
//			refreshLabelNumberError();
		} catch (Exception e) {
			Outside.err(this, "_refresh()", e);
		}
	}

	public void updateTable() throws Exception {
		buildDataFiltered();
		labelNumber.setText(" " + getFilteredNumber() + "  ");
		model.fireTableDataChanged();
	}

	private void buildDataFiltered() throws Exception {
		if (dataFull == null) {
			dataFiltered = null;
			return;
		}

		String search = (String) fieldHolder.g();
		dataFiltered = (List) filterList.t(new Object[] { dataFull, search, devId(), lockSet(), errorSet() });
	}

	private int getErrorNumber(String entityName) {
		try {
			Map errorMap = errorMap();
			if (errorMap == null || !errorMap.containsKey(entityName))
				return 0;
			return ((List) errorMap.get(entityName)).size();
		} catch (Exception e) {
			Outside.err(this, "getErrorNumber(String)", e);
		}
		return 0;
	}

	private Color getEntityForeground(String entityName) {
		try {
			Map errorMap = errorMap();
			if (errorMap == null || !errorMap.containsKey(entityName))
				return FG_COMPILED;
			return FG_UNCOMPILED;
		} catch (Exception e) {
			Outside.err(this, "getEntityForeground(String)", e);
		}
		return FG_COMPILED;
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

	private String getSelection() {
		if (table.getSelectionModel().isSelectionEmpty())
			return null;
		int row = table.getSelectedRow();
		return getNameAt(row);
	}
	
	/*
	 * ENTITY INFOS
	 */
	
	private boolean isLocked(String yName)
	{
		try
		{
			Set lockSet = lockSet();
			return lockSet!=null && lockSet.contains(yName);
		}
		catch(Exception e)
		{Outside.err(this,"isLocked(String)",e);}
		return false;
	}
	
	/*
	 * EVENTS
	 */
	
	private void selectionChanged() {
		send(this, "selectionChanged()");
	}
}
