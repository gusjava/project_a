package a.entity.gus.y.docview1.gui1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;
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

import a.framework.*;

public class EntityImpl implements Entity, P, I, V, ListSelectionListener {
	public String creationDate() {return "20231231";}

	public static final Color COLOR_SELECT = new Color(244, 244, 244);
	public static final Color COLOR_UNSELECT = Color.WHITE;

	private Service buildList;
	
	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	private JTextArea area;
	private Icon icon;

	public EntityImpl() throws Exception {
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
		List data = (List) buildList.t(obj);
		
		Vector vec = new Vector(data);
		list.setListData(vec);
		labelNumber.setText(" " + vec.size());
		
		area.setText("");
	}
	
	public Object i() throws Exception {
		return panel;
	}
	
	public void v(String key, Object obj) throws Exception {
		if(key.equals("icon")) {icon = (Icon) obj;return;}
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
