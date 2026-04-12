package a.entity.gus.y.docview1.gui4a;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, P, I, V, ListSelectionListener {
	public String creationDate() {return "20240109";}

	public static final Color COLOR_SELECT = new Color(244, 244, 244);
	public static final Color COLOR_UNSELECT = Color.WHITE;

	private Service gui1;
	
	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	private Icon icon;
	private String delim = ".";

	public EntityImpl() throws Exception {
		gui1 = Outside.service(this, "*gus.y.docview1.gui4");
		gui1.v("delim","#");
		
		list = new JList();
		list.setCellRenderer(new ListRenderer0());
		
		labelNumber = new JLabel(" ");

		JPanel p1 = new JPanel(new BorderLayout());
		p1.add(new JScrollPane(list), BorderLayout.CENTER);
		p1.add(labelNumber, BorderLayout.SOUTH);

		JPanel p2 = new JPanel(new BorderLayout());
		p2.add((JComponent) gui1.i(), BorderLayout.CENTER);

		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(200);
		split.setLeftComponent(p1);
		split.setRightComponent(p2);
		
		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);
		
		list.addListSelectionListener(this);
	}
	
	public void p(Object obj) throws Exception {
		Map pathsByDev = (Map) obj;
		
		List devList = new ArrayList(pathsByDev.keySet());
		Collections.sort(devList);
		
		List data = new ArrayList();
		for(int i=0;i<devList.size();i++) {
			String dev = (String) devList.get(i);
			List pathsForDev = (List) pathsByDev.get(dev);

			Map pathsByAppli = new HashMap();
			for(int j=0;j<pathsForDev.size();j++) {
				String path = (String) pathsForDev.get(j);
				String[] n = path.split("\\/");
				
				String appliPart = n[n.length-2];
				String appliName = dev+delim+appliPart;
				
				addToMap(pathsByAppli, appliName, path);
			}
			
			List appliNames = new ArrayList(pathsByAppli.keySet());
			Collections.sort(appliNames);
			
			for(int j=0;j<appliNames.size();j++) {
				String appliName = (String) appliNames.get(j);
				List pathsForAppli = (List) pathsByAppli.get(appliName);
				data.add(new Object[] {appliName, pathsForAppli});
			}
		}
		
		Vector vec = new Vector(data);
		list.setListData(vec);
		labelNumber.setText(" " + vec.size());
		
		gui1.p(null);
	}
	
	private void addToMap(Map map, String key, String value) {
		if(!map.containsKey(key)) map.put(key, new ArrayList<>());
		((List) map.get(key)).add(value);
	}
	
	public Object i() throws Exception {
		return panel;
	}
	
	public void v(String key, Object obj) throws Exception {
		if(key.equals("icon")) {icon = (Icon) obj;return;}
		if(key.equals("delim")) {delim = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}

	public void valueChanged(ListSelectionEvent e) {
		selectionChanged();
	}
	
	private void selectionChanged() {
		try {
			if (list.isSelectionEmpty()) {
				gui1.p(null);
				return;
			}

			Object[] infos = (Object[]) list.getSelectedValue();
			gui1.p(infos[1]);
		} catch (Exception e) {
			Outside.err(this, "selectionChanged()", e);
		}
	}
	
	private class ListRenderer0 extends JLabel implements ListCellRenderer {
		private Font font_p;
		private Font font_i;

		public ListRenderer0() {
			super();
			setOpaque(true);
			font_p = getFont().deriveFont(Font.PLAIN);
			font_i = getFont().deriveFont(Font.ITALIC);

			setBackground(COLOR_UNSELECT);
			setFont(font_p);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			setText(getText(value));
			setBackground(getBackground(isSelected));
			setIcon(icon);
			return this;
		}

		private String getText(Object value) {
			return (String) ((Object[]) value)[0];
		}

		private Color getBackground(boolean isSelected) {
			return isSelected ? COLOR_SELECT : COLOR_UNSELECT;
		}
	}
}
