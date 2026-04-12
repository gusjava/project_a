package a.entity.gus.y.dataview1.feature.r.gui2;

import java.awt.BorderLayout;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.E;
import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I, ListSelectionListener {
	public String creationDate() {return "20240125";}

	private Service viewer;
	private Service splitCust;
	private Service listRenderer;

	private JSplitPane split;
	private JList list;
	private JLabel label;

	private R data;
	private String[] keys;

	public EntityImpl() throws Exception {
		viewer = Outside.service(this, "*gus.y.dataview1.object");
		splitCust = Outside.service(this, "gus.x.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this, "gus.y.dataview1.list.renderer");

		list = new JList();
		list.addListSelectionListener(this);
		listRenderer.p(list);

		label = new JLabel(" ");

		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		p.add(label, BorderLayout.SOUTH);

		split = new JSplitPane();
		splitCust.p(split);

		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());
	}

	public Object i() throws Exception {
		return split;
	}

	public Object g() throws Exception {
		return data;
	}

	public void p(Object obj) throws Exception {
		data = (R) obj;
		keys = (String[]) data.r("keys");
		updateGui();
	}

	private void updateGui() {
		SwingUtilities.invokeLater((Runnable) this::updateGui_);
	}

	private void updateGui_() {
		try {
			Vector vec = new Vector();
			for(String key : keys) vec.add(key);
			Collections.sort(vec);
			list.setListData(vec);

			label.setText(" " + keys.length);
			viewer.p(null);
		} catch (Exception e) {
			Outside.err(this, "updateGui_()", e);
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		selectionChanged();
	}

	private void selectionChanged() {
		try {
			if (list.isSelectionEmpty()) {
				viewer.p(null);
				return;
			}
			String key = (String) list.getSelectedValue();
			Object value = data.r(key);
			viewer.p(value);
		} catch (Exception e) {
			Outside.err(this, "selectionChanged()", e);
		}
	}
}