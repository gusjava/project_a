package a.entity.gus.y.dataview1.map;

import java.awt.BorderLayout;
import java.util.Collections;
import java.util.Map;
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
	private JLabel labelNumber;
	private JLabel labelTitle;

	private S1 selectionSup;
	private Map data;

	public EntityImpl() throws Exception {
		viewer = Outside.service(this, "*gus.y.dataview1.object");
		splitCust = Outside.service(this, "gus.x.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this, "gus.x.swing.list.cust.renderer.obj");
		buildSelectionSup = Outside.service(this, "gus.x.swing.list.selection.buildsupport");

		list = new JList();
		listRenderer.p(list);

		labelNumber = new JLabel(" ");
		labelTitle = new JLabel(" ");

		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(new JScrollPane(list), BorderLayout.CENTER);
		panel1.add(labelNumber, BorderLayout.SOUTH);

		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(labelTitle, BorderLayout.NORTH);
		panel2.add((JComponent) viewer.i(), BorderLayout.CENTER);

		split = new JSplitPane();
		splitCust.p(split);

		split.setLeftComponent(panel1);
		split.setRightComponent(panel2);
		
		selectionSup = (S1) buildSelectionSup.t(list);
		selectionSup.addActionListener(e -> handleSelection());
	}

	public Object g() throws Exception {
		return data;
	}

	public void p(Object obj) throws Exception {
		data = (Map) obj;
		if (data == null) resetGui();
		else updateGui();
	}

	public void e() throws Exception {
		if (data != null) reload();
	}

	public Object i() throws Exception {
		return split;
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
				Vector vec = new Vector(data.keySet());
				Collections.sort(vec);
				
				selectionSup.setActivated(false);
				list.setListData(vec);
				labelNumber.setText(" " + data.size());
				labelTitle.setText(" ");
				selectionSup.setActivated(true);
				
				viewer.p(null);
			}

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
				Vector vec = new Vector(data.keySet());
				Collections.sort(vec);
				Object selected = list.getSelectedValue();
				
				selectionSup.setActivated(false);
				list.setListData(vec);
				if (selected != null && data.containsKey(selected)) 
					list.setSelectedValue(selected, true);
				selectionSup.setActivated(true);
			}

			labelNumber.setText(" " + data.size());
			if (list.isSelectionEmpty()) {
				labelTitle.setText(" ");
				viewer.p(null);
			}
		} catch (Exception e) {
			Outside.err(this, "reload_()", e);
		}
	}

	private void resetGui() throws Exception {
		selectionSup.setActivated(false);
		list.setListData(new Vector());
		labelNumber.setText(" ");
		labelTitle.setText(" ");
		selectionSup.setActivated(true);
		
		viewer.p(null);
	}

	private void handleSelection() {
		try {
			if (list.isSelectionEmpty()) {
				viewer.p(null);
				return;
			}
			Object key = list.getSelectedValue();
			labelTitle.setText(" " + key);
			viewer.p(data.get(key));
		} catch (Exception e) {
			Outside.err(this, "handleSelection()", e);
		}
	}
}
