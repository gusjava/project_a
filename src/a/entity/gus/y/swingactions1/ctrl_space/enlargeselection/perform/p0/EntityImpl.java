package a.entity.gus.y.swingactions1.ctrl_space.enlargeselection.perform.p0;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	private Service performMore;
	private Service buildDelim;
	private String delim;

	public EntityImpl() throws Exception {
		performMore = Outside.service(this, "gus.y.swingactions1.ctrl_space.enlargeselection.perform.p1");
		buildDelim = Outside.service(this, "gus.x.string.split.words1.delim");
		delim = (String) buildDelim.g();
	}

	public void p(Object obj) throws Exception {
		JTextComponent comp = (JTextComponent) obj;

		String text = comp.getText();
		int length = text.length();
		int pos = comp.getCaretPosition();

		int start = pos - 1;
		int end = pos;

		char c_before = start >= 0 ? text.charAt(start) : '~';
		char c_after = end < length ? text.charAt(end) : '~';

		if (c_before == '"') {
			end = reachNext(text, length, end, '"');
		} else if (c_before == '\'') {
			end = reachNext(text, length, end, '\'');
		} else if (c_before == '`') {
			end = reachNext(text, length, end, '`');
		} else if (c_before == '>') {
			end = reachNext(text, length, end, '<');
		} else if (c_before == '<') {
			end = reachClosing(text, length, end + 1, '<', '>');
		} else if (c_before == '(') {
			end = reachClosing(text, length, end, '(', ')');
		} else if (c_before == '[') {
			end = reachClosing(text, length, end + 1, '[', ']');
		} else if (c_before == '{') {
			end = reachClosing(text, length, end + 1, '{', '}');
		} else if (c_before == '>') {
			// A FAIRE
			// ici il faut détecter si le caret est juste après un tag xml ouvrant <name> ou
			// <name ...>
			// et sélection jusqu'au caractère avant le tag xml fermant correspondant
			// </name>
		}

		else if (c_after == '"') {
			end = reachNext(text, length, end + 1, '"');
			if (end < length)
				end++;
		} else if (c_after == '\'') {
			end = reachNext(text, length, end + 1, '\'');
			if (end < length)
				end++;
		} else if (c_after == '`') {
			end = reachNext(text, length, end + 1, '`');
			if (end < length)
				end++;
		} else if (c_after == '<') {
			end = reachClosing(text, length, end + 1, '<', '>');
			if (end < length)
				end++;
		} else if (c_after == '(') {
			end = reachClosing(text, length, end + 1, '(', ')');
			if (end < length)
				end++;
		} else if (c_after == '[') {
			end = reachClosing(text, length, end + 1, '[', ']');
			if (end < length)
				end++;
		} else if (c_after == '{') {
			end = reachClosing(text, length, end + 1, '{', '}');
			if (end < length)
				end++;
		} else {
			while (start >= 0 && !isWordDelim(text.charAt(start)))
				start--;
			while (end < length && !isWordDelim(text.charAt(end)))
				end++;
		}

		if (start == end - 1)
			performMore.p(comp);
		else
			comp.select(start + 1, end);
	}

	private int reachNext(String text, int length, int pos, char last) {
		while (pos < length && text.charAt(pos) != last)
			pos++;
		return pos;
	}

	private int reachClosing(String text, int length, int pos, char opening, char closing) {
		int k = 0;
		char c = text.charAt(pos);
		while (pos < length - 1 && (c != closing || k > 0)) {
			if (c == opening)
				k++;
			else if (c == closing)
				k--;
			c = text.charAt(++pos);
		}
		return pos;
	}

	private boolean isWordDelim(char c) {
		return delim.indexOf(c) >= 0;
	}
}
