package a.entity.gus.y.appview2.gui4.config;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl implements Entity, P, I, ListSelectionListener {
	public String creationDate() {return "20231128";}
	
	public static final int PATH_OFFSET = 4;
	public static final String ICONID = "ELEMENT_config";

	private Service findSrcLocation;
	private Service buildJList;
	private Service configViewer;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;

	private File appLocation;
	private File srcLocation;
	private List entries;
	private Map map;

	public EntityImpl() throws Exception {
		findSrcLocation = Outside.service(this, "gus.y.appview2.find.srclocation");
		buildJList = Outside.service(this, "gus.y.swing1.list.build.fromicon");
		configViewer = Outside.service(this, "*gus.y.appview2.gui4.config.detail");

		list = (JList) buildJList.t(ICONID);
		list.addListSelectionListener(this);

		labelNumber = new JLabel(" ");

		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		p.add(labelNumber, BorderLayout.SOUTH);

		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(200);

		split.setLeftComponent(p);
		split.setRightComponent((JComponent) configViewer.i());

		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		appLocation = (File) o[0];
		entries = (List) o[1];

		srcLocation = (File) findSrcLocation.t(appLocation);

		map = new HashMap();
		int nb = entries.size();
		for (int i = 0; i < nb; i++) {
			String entry = (String) entries.get(i);
			String configName = findConfigName(entry);
			if(configName!=null) {
				if (!map.containsKey(configName))
					map.put(configName, new ArrayList());
				((List) map.get(configName)).add(entry);
			}
		}

		Vector vec = new Vector(map.keySet());
		Collections.sort(vec);
		list.setListData(vec);
		labelNumber.setText(" " + vec.size());

		configViewer.p(null);
	}

	private String findConfigName(String path) throws Exception {
		String[] nn = path.split("[\\\\\\/]");
		if (nn.length <= PATH_OFFSET) return null;
		return nn[PATH_OFFSET-2] + "." + nn[PATH_OFFSET-1];
	}

	public Object i() throws Exception {
		return panel;
	}

	public void valueChanged(ListSelectionEvent e) {
		selectionChanged();
	}

	private void selectionChanged() {
		try {
			if (list.isSelectionEmpty()) {
				configViewer.p(null);
				return;
			}

			String name = (String) list.getSelectedValue();
			List javaEntries = (List) map.get(name);

			configViewer.p(new Object[] { srcLocation, javaEntries });
		} catch (Exception e) {
			Outside.err(this, "selectionChanged()", e);
		}
	}
}
