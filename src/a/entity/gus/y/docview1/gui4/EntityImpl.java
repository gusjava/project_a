package a.entity.gus.y.docview1.gui4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
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

	private Service pathToText;
	private Service buildList;
	
	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	private JTextArea area;
	private Icon icon;
	private String delim = ".";

	public EntityImpl() throws Exception {
		pathToText = Outside.service(this, "gus.y.docview1.pathtotext");
		buildList = Outside.service(this, "gus.y.docview1.buildlist");
		
		list = new JList();
		list.setCellRenderer(new ListRenderer0());
		
		labelNumber = new JLabel(" ");
		
		area = new JTextArea();
		area.setMargin(new Insets(5, 5, 5, 5));
		area.setEditable(false);
		area.setFont(area.getFont().deriveFont((float) 18));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);

		JPanel p1 = new JPanel(new BorderLayout());
		p1.add(new JScrollPane(list), BorderLayout.CENTER);
		p1.add(labelNumber, BorderLayout.SOUTH);

		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(new JScrollPane(area), BorderLayout.CENTER);

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
		List paths = (List) obj;
		if(paths==null) return;

		List data = new ArrayList();
		for(int i=0;i<paths.size();i++) {
			String path = (String) paths.get(i);
			String fileName = pathToLastPart(path);
			String src = (String) pathToText.t(path);
			List l = (List) buildList.t(src);
			
			for(int j=0;j<l.size();j++) {
				String[] infos = (String[]) l.get(j);
				data.add(new String[] {fileName+delim+infos[0], infos[1]});
			}
		}
		
		Vector vec = new Vector(data);
		list.setListData(vec);
		labelNumber.setText(" " + vec.size());
		
		area.setText("");
	}
	
	private String pathToLastPart(String path) {
		String[] n = path.split("\\/");
		String fileName = n[n.length-1];
		return fileName.substring(0, fileName.length()-4);
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
		if (list.isSelectionEmpty()) {
			area.setText("");
			return;
		}
		
		String[] infos = (String[]) list.getSelectedValue();
		area.setText(infos[1]);
		area.setCaretPosition(0);
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
			return ((String[]) value)[0];
		}

		private Color getBackground(boolean isSelected) {
			return isSelected ? COLOR_SELECT : COLOR_UNSELECT;
		}
	}
}
