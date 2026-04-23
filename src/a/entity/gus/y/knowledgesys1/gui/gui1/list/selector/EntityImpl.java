package a.entity.gus.y.knowledgesys1.gui.gui1.list.selector;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, I, V, ActionListener, ListSelectionListener {
	public String creationDate() {return "20260418";}

	private Object engine;
	private JPanel panel;
	private JList list;
	private DefaultListModel model;

	public EntityImpl() throws Exception {
		model = new DefaultListModel();
		list = new JList(model);
		list.setCellRenderer(new CellRenderer0());

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list), BorderLayout.CENTER);

		list.addListSelectionListener(this);

		list.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("F5"), "reload");
		list.getActionMap().put("reload", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {reload();}
		});
	}

	public void v(String key, Object obj) throws Exception {
		if (key.equals("engine")) initEngine(obj);
	}

	private void initEngine(Object engine) throws Exception {
		if (this.engine != null)
			((S) this.engine).removeActionListener(this);
		this.engine = engine;
		((S) engine).addActionListener(this);
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
			List all = (engine != null) ? (List) ((R) engine).r("all") : null;
			model.clear();
			if (all != null) {
				for (int i = 0; i < all.size(); i++)
					model.addElement(all.get(i));
			}
		} catch (Exception e) {
			Outside.err(this, "loaded()", e);
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) selected();
	}

	private void selected() {
		send(this, "selected()");
	}

	private class CellRenderer0 extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Map) {
				Map m = (Map) value;
				label.setText(m.get("code") + ":" + m.get("action") + ":" + m.get("object"));
			}
			return label;
		}
	}
}
