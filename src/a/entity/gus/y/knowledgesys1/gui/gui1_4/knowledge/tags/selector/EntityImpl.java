package a.entity.gus.y.knowledgesys1.gui.gui1_4.knowledge.tags.selector;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, I, V, ActionListener, ListSelectionListener {
	public String creationDate() {return "20260429";}

	public static final String DISPLAY_DELETE = "KNOWLEDGE_delete#Delete [DEL]";
	public static final String DISPLAY_RENAME = "KNOWLEDGE_rename#Rename [F2]";

	private Service buildAction;
	private Service fieldHolder;
	private Service toolbarFactory;
	private Service deleteByTag;
	private Service renameByTag;
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
	private Action actionRename;

	public EntityImpl() throws Exception {
		buildAction    = Outside.service(this, "gus.y.swing1.action.builder1");
		fieldHolder    = Outside.service(this, "*gus.y.swing1.textfield.editor1");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		deleteByTag    = Outside.service(this, "gus.y.knowledgedb1.knowledge_tag.deletebytag");
		renameByTag    = Outside.service(this, "gus.y.knowledgedb1.knowledge_tag.renamebytag");
		linker         = Outside.service(this, "gus.x.swing.table.textfield.linker");

		actionDelete = (Action) buildAction.t(new Object[] { DISPLAY_DELETE, (E) this::del_delete });
		actionRename = (Action) buildAction.t(new Object[] { DISPLAY_RENAME, (E) this::f2_rename });
		actionDelete.setEnabled(false);
		actionRename.setEnabled(false);

		bar = (JToolBar) toolbarFactory.i();
		bar.add(actionDelete);
		bar.add(actionRename);

		field = (JComponent) fieldHolder.i();
		field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_DELETE) del_delete();
				else if (code == KeyEvent.VK_F2) f2_rename();
				else if (code == KeyEvent.VK_F5) reload();
			}
		});

		model = new DefaultTableModel(new String[]{"Tag", "Count"}, 0) {
			public boolean isCellEditable(int r, int c) { return false; }
			public Class getColumnClass(int c) { return c == 1 ? Long.class : String.class; }
		};
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sorter = new TableRowSorter(model);
		table.setRowSorter(sorter);
		table.getSelectionModel().addListSelectionListener(this);
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_DELETE) del_delete();
				else if (code == KeyEvent.VK_F2) f2_rename();
				else if (code == KeyEvent.VK_F5) reload();
			}
		});

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

	public Object g() throws Exception {
		int row = table.getSelectedRow();
		if (row < 0) return null;
		int modelRow = table.convertRowIndexToModel(row);
		String tag = (String) model.getValueAt(modelRow, 0);
		Long count = (Long) model.getValueAt(modelRow, 1);
		Map m = new HashMap();
		m.put("tag", tag);
		m.put("count", count);
		return m;
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
			Map tags = engine != null ? (Map) ((R) engine).r("knowledgeTags") : null;
			String prevTag = getSelectedTag();
			model.setRowCount(0);
			if (tags != null) {
				for (Object key : tags.keySet())
					model.addRow(new Object[]{key, tags.get(key)});
			}
			applyFilter();
			if (prevTag != null) restoreSelection(prevTag);
		} catch (Exception e) {
			Outside.err(this, "loaded()", e);
		}
	}

	private String getSelectedTag() {
		int row = table.getSelectedRow();
		if (row < 0) return null;
		return (String) model.getValueAt(table.convertRowIndexToModel(row), 0);
	}

	private void restoreSelection(String tag) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if (tag.equals(model.getValueAt(i, 0))) {
				int viewRow = table.convertRowIndexToView(i);
				if (viewRow >= 0) {
					table.setRowSelectionInterval(viewRow, viewRow);
					table.scrollRectToVisible(table.getCellRect(viewRow, 0, true));
				}
				return;
			}
		}
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
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search.trim(), 0));
			labelNumber.setText(table.getRowCount() + " / " + model.getRowCount());
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			selected();
			refreshActions();
		}
	}

	private void selected() {
		send(this, "selected()");
	}

	private void refreshActions()
	{
		try
		{
			boolean sel = table.getSelectedRow() >= 0;
			actionDelete.setEnabled(sel);
			actionRename.setEnabled(sel);
		}
		catch(Exception e)
		{Outside.err(this,"refreshActions()",e);}
	}

	private void del_delete() {
		String tag = getSelectedTag();
		if (tag == null) return;
		int res = JOptionPane.showConfirmDialog(panel,
			"Delete tag \"" + tag + "\" from all knowledges?",
			"Confirm delete", JOptionPane.YES_NO_OPTION);
		if (res != JOptionPane.YES_OPTION) return;
		try {
			Connection cx = (Connection) ((R) engine).r("cx");
			deleteByTag.t(new Object[]{cx, tag});
			reload();
		} catch (Exception e) { Outside.err(this, "del_delete()", e); }
	}

	private void f2_rename() {
		String tag = getSelectedTag();
		if (tag == null) return;
		String newTag = (String) JOptionPane.showInputDialog(panel,
			"New name for tag:", "Rename tag",
			JOptionPane.PLAIN_MESSAGE, null, null, tag);
		if (newTag == null || newTag.trim().isEmpty()) return;
		newTag = newTag.trim();
		if (newTag.equals(tag)) return;
		try {
			Connection cx = (Connection) ((R) engine).r("cx");
			renameByTag.t(new Object[]{cx, tag, newTag});
			reload();
		} catch (Exception e) { Outside.err(this, "f2_rename()", e); }
	}
}
