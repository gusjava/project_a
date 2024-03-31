package a.entity.gus.y.swingactions1.ctrl_f.search.perform;

import a.framework.*;
import javax.swing.text.*;
import java.util.regex.*;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	public static final String KEY_HANDLER = "ctrl_f_handler";

	public static final String PAINTER_KEY1 = "search";
	public static final String PAINTER_KEY2 = "search_i";

	private Service getPainter;

	private DefaultHighlighter.DefaultHighlightPainter painter1;
	private DefaultHighlighter.DefaultHighlightPainter painter2;

	public EntityImpl() throws Exception {
		getPainter = Outside.service(this, "gus.y.swingactions1.painter.manager");

		painter1 = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY1);
		painter2 = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY2);
	}

	public void p(Object obj) throws Exception {
		perform((JTextComponent) obj);
	}

	private void perform(JTextComponent comp) throws Exception {
		String selection = selection(comp);

		Highlighter high = comp.getHighlighter();
		boolean removed = removeHighlights(high);
		if (removed)
			return;

		String exp = findExpression(selection);
		if (exp == null || exp.equals(""))
			return;

		Pattern p = Pattern.compile(Pattern.quote(exp), Pattern.DOTALL);
		String text = comp.getText();
		Matcher m = p.matcher(text);

		while (m.find()) {
			int start = m.start();
			int end = m.end();
			high.addHighlight(start, end, painter1);
		}
	}

	private boolean removeHighlights(Highlighter high) {
		Highlighter.Highlight[] h = high.getHighlights();
		boolean removed = false;
		for (int i = 0; i < h.length; i++)
			if (h[i].getPainter().equals(painter1) || h[i].getPainter().equals(painter2)) {
				high.removeHighlight(h[i]);
				removed = true;
			}
		return removed;
	}

	private String findExpression(String selection) {
		if (selection != null)
			return selection;
		return JOptionPane.showInputDialog(null, "Please, type an expression:", "Search expression",
				JOptionPane.PLAIN_MESSAGE);
	}

	private String selection(JTextComponent comp) {
		String s = comp.getSelectedText();
		if (s != null && !s.equals(""))
			return s;
		return null;
	}
}