package a.entity.gus.y.dataview1.list;

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

import a.framework.*;

public class EntityImpl implements Entity, G, P, I, R, E {
	public String creationDate() {return "20231129";}

	private Service viewer;
	private Service splitCust;
	private Service listRenderer;
	private Service buildSelectionSup;

	private JSplitPane split;
	private JList list;
	private JLabel label;

	private S1 selectionSup;
	private List data;

	public EntityImpl() throws Exception {
		viewer = Outside.service(this, "*gus.y.dataview1.object");
		splitCust = Outside.service(this, "gus.x.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this, "gus.y.dataview1.list.renderer");
		buildSelectionSup = Outside.service(this, "gus.x.swing.list.selection.buildsupport");

		list = new JList();
		listRenderer.p(list);

		label = new JLabel(" ");

		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		p.add(label, BorderLayout.SOUTH);

		split = new JSplitPane();
		splitCust.p(split);

		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());
		
		selectionSup = (S1) buildSelectionSup.t(list);
		selectionSup.addActionListener(e -> handleSelection());
	}

	public Object i() throws Exception {
		return split;
	}

	public Object g() throws Exception {
		return data;
	}

	public void p(Object obj) throws Exception {
		data = (List) obj;
		if (data == null)
			resetGui();
		else
			updateGui();
	}

	public void e() throws Exception {
		if (data != null)
			reload();
	}

	public Object r(String key) throws Exception {
		if (key.equals("list"))
			return list;
		if (key.equals("keys"))
			return new String[] { "list" };
		throw new Exception("Unknown key: " + key);
	}

	private void updateGui() {
		SwingUtilities.invokeLater((Runnable) this::updateGui_);
	}

	private void updateGui_() {
		try {
			synchronized (data) {
				Vector vec = new Vector(data);
				Collections.sort(vec);
				list.setListData(vec);
			}

			label.setText(" " + data.size());
			viewer.p(null);
		} catch (Exception e) {
			Outside.err(this, "updateGui_()", e);
		}
	}

	private void reload() {
		SwingUtilities.invokeLater((Runnable) this::reload_);
	}

	private void reload_() {
		try {
			synchronized (data) {
				Vector vec = new Vector(data);
				Collections.sort(vec);
				Object selected = list.getSelectedValue();
				
				selectionSup.setActivated(false);
				list.setListData(vec);
				if (selected != null && data.contains(selected)) 
					list.setSelectedValue(selected, true);
				selectionSup.setActivated(true);
			}

			label.setText(" " + data.size());
			if (list.isSelectionEmpty()) {
				viewer.p(null);
			}
		} catch (Exception e) {
			Outside.err(this, "reload_()", e);
		}
	}

	private void resetGui() throws Exception {
		list.setListData(new Vector());
		label.setText(" ");
		viewer.p(null);
	}

	private void handleSelection() {
		try {
			if (list.isSelectionEmpty()) {
				viewer.p(null);
				return;
			}
			Object value = list.getSelectedValue();
			viewer.p(value);
		} catch (Exception e) {
			Outside.err(this, "handleSelection()", e);
		}
	}
}