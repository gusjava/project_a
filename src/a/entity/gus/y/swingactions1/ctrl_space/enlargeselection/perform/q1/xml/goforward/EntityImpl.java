package a.entity.gus.y.swingactions1.ctrl_space.enlargeselection.perform.q1.xml.goforward;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		JTextComponent comp = (JTextComponent) o[0];
		String name = (String) o[1];

		int start = comp.getSelectionStart();
		int end = comp.getSelectionEnd();
		String text = comp.getText();

		end = findNewPos(name, text, end);
		while (start > 0 && text.charAt(start - 1) != '\n')
			start--;

		comp.select(start, end);
	}

	private int findNewPos(String name, String text, int pos) {
		int k = 1;
		int length = text.length();

		String openTag = "<" + name + ">";
		String openTag1 = "<" + name + " ";
		String closeTag = "</" + name + ">";
		String closeTag1 = "</" + name + " ";

		boolean insideString = false;
		StringBuffer b = null;

		while (k > 0 && pos < length) {
			char c = text.charAt(pos);

			if (c == '<') {
				if (!insideString) {
					b = new StringBuffer();
					b.append(c);
				}
			} else if (c == '>') {
				if (b != null && !insideString) {
					b.append(c);
					String tag = b.toString();
					b = null;

					if (tag.equals(openTag))
						k++;
					else if (tag.startsWith(openTag1) && !tag.endsWith("/>"))
						k++;

					else if (tag.equals(closeTag))
						k--;
					else if (tag.startsWith(closeTag1))
						k--;
				}
			} else if (c == '"') {
				if (b != null)
					insideString = !insideString;
			} else if (isWhite(c)) {
				if (b != null && !insideString) {
					char c0 = last(b);
					if (c0 != '<' && c0 != '/')
						b.append(" ");
				}
			} else {
				if (b != null && !insideString) {
					b.append(c);
				}
			}
			pos++;
		}

		return pos;
	}

	private boolean isWhite(char c) {
		return c == ' ' || c == '\t' || c == '\n';
	}

	private char last(StringBuffer b) {
		return b.charAt(b.length() - 1);
	}

}
