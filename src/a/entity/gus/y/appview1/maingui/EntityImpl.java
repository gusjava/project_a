package a.entity.gus.y.appview1.maingui;

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I, ListSelectionListener {
	public String creationDate() {return "20231128";}

	private Service buildEntries;
	private Service splitCust;
	private Service listRenderer;
	private Service viewer;
	private Service getIcon;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	private JLabel labelTitle;

	private File file;

	public EntityImpl() throws Exception {
		buildEntries = Outside.service(this, "gus.y.appentries1.build");
		splitCust = Outside.service(this, "gus.x.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this, "gus.y.swing1.list.cust.renderer.icon.ext");
		viewer = Outside.service(this, "*gus.y.appview1.entryview");
		getIcon = Outside.service(this, "gus.y.files1.icon");

		list = new JList();
		list.addListSelectionListener(this);
		listRenderer.p(list);

		labelNumber = new JLabel(" ");
		labelTitle = new JLabel(" ");

		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		p.add(labelNumber, BorderLayout.SOUTH);

		JSplitPane split = new JSplitPane();
		splitCust.p(split);

		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());

		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(split, BorderLayout.CENTER);
	}

	public Object i() throws Exception {
		return panel;
	}

	public Object g() throws Exception {
		return file;
	}

	public void p(Object obj) throws Exception {
		file = (File) obj;

		if (file == null || !file.exists())
			resetGui();
		else
			updateGui();
	}

	private void resetGui() throws Exception {
		list.setListData(new Vector());
		labelNumber.setText(" ");

		labelTitle.setText(" ");
		labelTitle.setIcon(null);

		viewer.p(null);
	}

	private void updateGui() throws Exception {
		List entries = (List) buildEntries.t(file);

		Vector vec = new Vector(entries);
		list.setListData(vec);
		labelNumber.setText(" " + entries.size());

		labelTitle.setText(file.getAbsolutePath());
		labelTitle.setIcon((Icon) getIcon.t(file));

		viewer.p(null);
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
			String entry = (String) list.getSelectedValue();
			viewer.p(new Object[] { file, entry });
		} catch (Exception e) {
			Outside.err(this, "selectionChanged()", e);
		}
	}
}
