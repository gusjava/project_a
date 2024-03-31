package a.entity.gus.y.swingactions1.ctrl_space.enlargeselection.perform.p2;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	private Service performMore;
	private Service buildDelim;
	private String delim;

	public EntityImpl() throws Exception {
		performMore = Outside.service(this, "gus.y.swingactions1.ctrl_space.enlargeselection.perform.p3");
		buildDelim = Outside.service(this, "gus.x.string.split.words2.delim");
		delim = (String) buildDelim.g();
	}

	public void p(Object obj) throws Exception {
		JTextComponent comp = (JTextComponent) obj;

		String text = comp.getText();
		int length = text.length();
		int start = comp.getSelectionStart() - 1;
		int end = comp.getSelectionEnd();

		boolean moved = false;
		while (start >= 0 && !isWordDelim(text.charAt(start))) {
			start--;
			moved = true;
		}
		while (end < length && !isWordDelim(text.charAt(end))) {
			end++;
			moved = true;
		}

		if (!moved)
			performMore.p(comp);
		else
			comp.select(start + 1, end);
	}

	private boolean isWordDelim(char c) {
		return delim.indexOf(c) >= 0;
	}
}
