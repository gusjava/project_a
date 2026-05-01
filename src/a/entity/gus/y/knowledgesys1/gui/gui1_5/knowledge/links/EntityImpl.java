package a.entity.gus.y.knowledgesys1.gui.gui1_5.knowledge.links;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, I, V, ActionListener {
	public String creationDate() {return "20260429";}

	public static final String DISPLAY_DELETE      = "KNOWLEDGE_delete#Delete [DEL]";
	public static final String DISPLAY_CHANGE_TYPE = "KNOWLEDGE_rename#Change type [F2]";

	private Service buildAction;
	private Service fieldHolder;
	private Service toolbarFactory;
	private Service deleteLink;
	private Service updateType;
	private Service linker;

	private Object engine;
	private JPanel panel;
	private JTable table;
	private DefaultTableModel model;
	private TableRowSorter sorter;
	private JComponent field;
	private JToolBar bar;
	private JLabel labelNumber;

	private Action actionDelete;
	private Action actionChangeType;

	public EntityImpl() throws Exception {
		buildAction    = Outside.service(this, "gus.y.swing1.action.builder1");
		fieldHolder    = Outside.service(this, "*gus.y.swing1.textfield.editor1");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		deleteLink     = Outside.service(this, "gus.y.knowledgedb1.knowledge_link.delete3");
		updateType     = Outside.service(this, "gus.y.knowledgedb1.knowledge_link.updatetype");
		linker         = Outside.service(this, "gus.x.swing.table.textfield.linker");

		actionDelete     = (Action) buildAction.t(new Object[] { DISPLAY_DELETE,      (E) this::del_delete });
		actionChangeType = (Action) buildAction.t(new Object[] { DISPLAY_CHANGE_TYPE, (E) this::f2_changeType });
		actionDelete.setEnabled(false);
		actionChangeType.setEnabled(false);

		bar = (JToolBar) toolbarFactory.i();
		bar.add(actionDelete);
		bar.add(actionChangeType);

		field = (JComponent) fieldHolder.i();
		field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if      (code == KeyEvent.VK_DELETE) del_delete();
				else if (code == KeyEvent.VK_F2)     f2_changeType();
				else if (code == KeyEvent.VK_F5)     reload();
			}
		});

		model = new DefaultTableModel(new String[]{"Linker", "Linked", "Type", "id_linker", "id_linked"}, 0) {
			public boolean isCellEditable(int r, int c) { return false; }
		};
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sorter = new TableRowSorter(model);
		table.setRowSorter(sorter);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) refreshActions();
			}
		});
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if      (code == KeyEvent.VK_DELETE) del_delete();
				else if (code == KeyEvent.VK_F2)     f2_changeType();
				else if (code == KeyEvent.VK_F5)     reload();
			}
		});

		TableColumnModel cm = table.getColumnModel();
		cm.removeColumn(cm.getColumn(4));
		cm.removeColumn(cm.getColumn(3));

		linker.p(new Object[]{table, field});

		labelNumber = new JLabel("");

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(bar, BorderLayout.WEST);
		bottomPanel.add(labelNumber, BorderLayout.EAST);

		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		fieldHolder.addActionListener(e -> handleInputEdition());
	}

	public void v(String key, Object obj) throws Exception {
		if (key.equals("engine")) initEngine(obj);
	}

	private void initEngine(Object engine) throws Exception {
		if (this.engine != null)
			((S) this.engine).removeActionListener(this);
		this.engine = engine;
		((S) engine).addActionListener(this);
		loaded();
	}

	private void reload() {
		try {((E) engine).e();}
		catch (Exception e) {Outside.err(this, "reload()", e);}
	}

	public Object i() throws Exception {
		return panel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("loaded()"))
			loaded();
	}

	private void loaded() {
		try {
			List links = engine != null ? (List) ((R) engine).r("knowledgeLinks") : null;
			model.setRowCount(0);
			if (links != null) {
				for (int i = 0; i < links.size(); i++) {
					Map m = (Map) links.get(i);
					model.addRow(new Object[]{
						m.get("linker"),
						m.get("linked"),
						m.get("type"),
						m.get("id_linker"),
						m.get("id_linked")
					});
				}
			}
			applyFilter();
		} catch (Exception e) {
			Outside.err(this, "loaded()", e);
		}
	}

	private int getSelectedModelRow() {
		int row = table.getSelectedRow();
		if (row < 0) return -1;
		return table.convertRowIndexToModel(row);
	}

	private void handleInputEdition() {
		try { applyFilter(); }
		catch (Exception e) { Outside.err(this, "handleInputEdition()", e); }
	}

	private void applyFilter() throws Exception {
		String search = (String) fieldHolder.g();
		if (search == null || search.trim().isEmpty()) {
			sorter.setRowFilter(null);
			labelNumber.setText(String.valueOf(model.getRowCount()));
		} else {
			String regex = "(?i)" + search.trim();
			List filters = new ArrayList();
			filters.add(RowFilter.regexFilter(regex, 0));
			filters.add(RowFilter.regexFilter(regex, 1));
			filters.add(RowFilter.regexFilter(regex, 2));
			sorter.setRowFilter(RowFilter.orFilter(filters));
			labelNumber.setText(table.getRowCount() + " / " + model.getRowCount());
		}
	}

	private void del_delete() {
		int modelRow = getSelectedModelRow();
		if (modelRow < 0) return;
		String linkerCode = (String) model.getValueAt(modelRow, 0);
		String linkedCode = (String) model.getValueAt(modelRow, 1);
		int res = JOptionPane.showConfirmDialog(panel,
			"Delete link \"" + linkerCode + "\" \u2192 \"" + linkedCode + "\"?",
			"Confirm delete", JOptionPane.YES_NO_OPTION);
		if (res != JOptionPane.YES_OPTION) return;
		try {
			Connection cx = (Connection) ((R) engine).r("cx");
			Long idLinker = (Long) model.getValueAt(modelRow, 3);
			Long idLinked = (Long) model.getValueAt(modelRow, 4);
			deleteLink.p(new Object[]{cx, idLinker, idLinked});
			reload();
		} catch (Exception e) { Outside.err(this, "del_delete()", e); }
	}

	private void f2_changeType() {
		int modelRow = getSelectedModelRow();
		if (modelRow < 0) return;
		String currentType = (String) model.getValueAt(modelRow, 2);
		String newType = (String) JOptionPane.showInputDialog(panel,
			"New type:", "Change type",
			JOptionPane.PLAIN_MESSAGE, null, null, currentType);
		if (newType == null || newType.trim().isEmpty()) return;
		newType = newType.trim();
		if (newType.equals(currentType)) return;
		try {
			Connection cx = (Connection) ((R) engine).r("cx");
			Long idLinker = (Long) model.getValueAt(modelRow, 3);
			Long idLinked = (Long) model.getValueAt(modelRow, 4);
			updateType.t(new Object[]{cx, idLinker, idLinked, newType});
			reload();
		} catch (Exception e) { Outside.err(this, "f2_changeType()", e); }
	}

	private void refreshActions()
	{
		try
		{
			boolean sel = getSelectedModelRow() >= 0;
			actionDelete.setEnabled(sel);
			actionChangeType.setEnabled(sel);
		}
		catch(Exception e)
		{Outside.err(this,"refreshActions()",e);}
	}
}
