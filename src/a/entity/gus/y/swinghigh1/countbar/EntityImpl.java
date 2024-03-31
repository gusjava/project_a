package a.entity.gus.y.swinghigh1.countbar;

import a.framework.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.Font;
import java.util.Map;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240113";}

	public Object t(Object obj) throws Exception {
		return new JToolBarCounter((JTextComponent) obj);
	}

	private class JToolBarCounter extends JToolBar implements ActionListener, CaretListener {
		private JTextComponent comp;
		private Map mapOcc;
		private Map mapLabel;

		private Color selected;

		public JToolBarCounter(JTextComponent comp) throws Exception {
			super();
			setFloatable(false);
			this.comp = comp;
			comp.addCaretListener(this);
			mapOcc = new HashMap();
			mapLabel = new HashMap();
			add(new JLabel(" "));
		}

		public void actionPerformed(ActionEvent e) {
			updateOcc();
		}

		public void caretUpdate(CaretEvent e) {
			udpateSelected();
		}

		private void updateOcc() {
			mapOcc.clear();
			mapLabel.clear();
			selected = null;

			Highlighter.Highlight[] ht = comp.getHighlighter().getHighlights();
			for (int i = 0; i < ht.length; i++) {
				Color c = color(ht[i]);
				if (isSelected(ht[i]))
					selected = c;
				addColor(c);
			}

			removeAll();
			if (mapOcc.isEmpty())
				add(new JLabel(" "));
			else
				addLabels();

			revalidate();
			repaint();
		}

		private void udpateSelected() {
			selected = null;

			Highlighter.Highlight[] ht = comp.getHighlighter().getHighlights();
			for (int i = 0; i < ht.length; i++) {
				if (isSelected(ht[i]))
					selected = color(ht[i]);
			}

			Iterator it = mapLabel.keySet().iterator();
			while (it.hasNext()) {
				Color c = (Color) it.next();
				JLabel l = (JLabel) mapLabel.get(c);
				updateLabelFont(l, c);
			}
		}

		private boolean isSelected(Highlighter.Highlight h) {
			int start = h.getStartOffset();
			int end = h.getEndOffset();
			int pos = comp.getCaretPosition();
			return start <= pos && pos <= end;
		}

		private void addColor(Color c) {
			if (!mapOcc.containsKey(c)) {
				mapOcc.put(c, new Integer(1));
				return;
			}

			Integer n = (Integer) mapOcc.get(c);
			mapOcc.put(c, new Integer(n.intValue() + 1));
		}

		private Color color(Highlighter.Highlight h) {
			Color c = ((DefaultHighlighter.DefaultHighlightPainter) h.getPainter()).getColor();
			return c != null ? c : comp.getSelectionColor();
		}

		private void addLabels() {
			Iterator it = mapOcc.keySet().iterator();
			while (it.hasNext())
				addLabel((Color) it.next());
		}

		private void addLabel(Color c) {
			Integer n = (Integer) mapOcc.get(c);

			JLabel l = new JLabel(" " + n);
			l.setOpaque(true);
			l.setBackground(Color.WHITE);

			l.setForeground(c);
			l.setToolTipText(tooltip(c));
			updateLabelFont(l, c);
			mapLabel.put(c, l);
			add(l);
		}

		private void updateLabelFont(JLabel l, Color c) {
			if (selected != null && selected.equals(c))
				l.setFont(l.getFont().deriveFont(Font.BOLD));
			else
				l.setFont(l.getFont().deriveFont(Font.PLAIN));
		}

		private String tooltip(Color c) {
			return "r=" + c.getRed() + ",g=" + c.getGreen() + ",b=" + c.getBlue();
		}
	}
}
