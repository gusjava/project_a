package a.entity.gus.y.knowledgesys1.gui.gui1_1.knowledge.list.selector;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, I, V, ActionListener, ListSelectionListener {
	public String creationDate() {return "20260423";}

	public static final String DISPLAY_ADD    = "KNOWLEDGE_add#Add [F1]";
	public static final String DISPLAY_EDIT   = "KNOWLEDGE_edit#Edit [F2]";
	public static final String DISPLAY_DELETE = "KNOWLEDGE_delete#Delete [DEL]";

	private Service buildAction;
	private Service fieldHolder;
	private Service toolbarFactory;
	private Service linkerField;
	private Service add;
	private Service edit;
	private Service delete;

	private Object engine;
	private JPanel panel;
	private JList list;
	private DefaultListModel model;
	private JComponent field;
	private JToolBar bar;
	private JLabel labelNumber;

	private Icon icon;

	private Action actionAdd;
	private Action actionEdit;
	private Action actionDelete;

	private List all = new ArrayList();
	private List filtered = new ArrayList();

	public EntityImpl() throws Exception {
		buildAction    = Outside.service(this, "gus.y.swing1.action.builder1");
		fieldHolder    = Outside.service(this, "*gus.y.swing1.textfield.editor1");
		toolbarFactory = Outside.service(this, "gus.x.swing.toolbar.factory1");
		linkerField = Outside.service(this, "gus.x.swing.list.textfield.linker");
		add    = Outside.service(this, "gus.y.knowledgesys1.gui.knowledge.add");
		edit   = Outside.service(this, "gus.y.knowledgesys1.gui.knowledge.edit");
		delete = Outside.service(this, "gus.y.knowledgesys1.gui.knowledge.delete");
		
		icon = (Icon) Outside.resource(this, "icon#KNOWLEDGE");

		actionAdd    = (Action) buildAction.t(new Object[] { DISPLAY_ADD,    (E) this::f1_add });
		actionEdit   = (Action) buildAction.t(new Object[] { DISPLAY_EDIT,   (E) this::f2_edit });
		actionDelete = (Action) buildAction.t(new Object[] { DISPLAY_DELETE, (E) this::del_delete });

		bar = (JToolBar) toolbarFactory.i();
		bar.add(actionAdd);
		bar.add(actionEdit);
		bar.add(actionDelete);

		field = (JComponent) fieldHolder.i();
		field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_F1) f1_add();
				else if (code == KeyEvent.VK_F2) f2_edit();
				else if (code == KeyEvent.VK_F5) reload();
			}
		});

		model = new DefaultListModel();
		list = new JList(model);
		list.setCellRenderer(new CellRenderer0());
		list.addListSelectionListener(this);
		list.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_F1) f1_add();
				else if (code == KeyEvent.VK_F2) f2_edit();
				else if (code == KeyEvent.VK_DELETE) del_delete();
				else if (code == KeyEvent.VK_F5) reload();
			}
		});

		labelNumber = new JLabel("");

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(bar, BorderLayout.WEST);
		bottomPanel.add(labelNumber, BorderLayout.EAST);

		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		linkerField.p(new Object[]{list, field});
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
		Object selected = list.getSelectedValue();
		if (selected instanceof Map) return selected;
		return null;
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
			all = retrieveList();
			refresh();
		} catch (Exception e) {
			Outside.err(this, "loaded()", e);
		}
	}
	
	private List retrieveList() throws Exception {
		if(engine==null) return new ArrayList();
		List list = (List) ((R) engine).r("knowledgeList");
		return list != null ? list : new ArrayList();
	}

	private void handleInputEdition() {
		try {
			refresh();
		} catch (Exception e) {
			Outside.err(this, "handleInputEdition()", e);
		}
	}

	private void refresh() throws Exception {
		String search = (String) fieldHolder.g();
		filtered = filter(all, search);
		Map previousSelection = (Map) g();
		model.clear();
		for (int i = 0; i < filtered.size(); i++)
			model.addElement(filtered.get(i));
		if (previousSelection != null) setSelection(previousSelection);
		if (search == null || search.trim().isEmpty())
			labelNumber.setText(String.valueOf(all.size()));
		else
			labelNumber.setText(filtered.size() + " / " + all.size());
	}

	private List filter(List source, String search) {
		if (search == null || search.trim().isEmpty()) return source;
		String s = search.trim().toLowerCase();
		List result = new ArrayList();
		for (int i = 0; i < source.size(); i++) {
			Map m = (Map) source.get(i);
			String display = (String) m.get("display");
			if (display.toLowerCase().contains(s)) result.add(m);
		}
		return result;
	}

	private void setSelection(Map target) {
		if (target == null) return;
		Object id = target.get("id");
		if (id == null) return;
		for (int i = 0; i < model.getSize(); i++) {
			Map m = (Map) model.getElementAt(i);
			if (id.equals(m.get("id"))) {
				list.setSelectedIndex(i);
				list.ensureIndexIsVisible(i);
				return;
			}
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) selected();
	}

	private void selected() {
		send(this, "selected()");
	}

	/*
	 * ACTIONS
	 */

	private void f1_add() {
		try {
			if (engine == null) return;
			add.p(engine);
		} catch (Exception e) {
			Outside.err(this, "f1_add()", e);
		}
	}

	private void f2_edit() {
		try {
			Map selected = (Map) g();
			if (selected == null) return;
			edit.p(new Object[]{engine, selected});
		} catch (Exception e) {
			Outside.err(this, "f2_edit()", e);
		}
	}

	private void del_delete() {
		try {
			Map selected = (Map) g();
			if (selected == null) return;
			delete.p(new Object[]{engine, selected});
		} catch (Exception e) {
			Outside.err(this, "del_delete()", e);
		}
	}

	private class CellRenderer0 extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			Map m = (Map) value;
			String display = (String) m.get("display");
			label.setText(display);
			label.setIcon(icon);
			
			setBackground(isSelected ? new Color(244, 244, 244) : Color.WHITE);
			return label;
		}
	}
}