package a.entity.gus.y.swingactions1.ctrl_e.doubleline.perform.caret;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	public void p(Object obj) throws Exception {
		JTextComponent comp = (JTextComponent) obj;

		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();

		int p = comp.getCaretPosition();
		Element element = document.getParagraphElement(p);

		int start = element.getStartOffset() - 1;
		int end = element.getEndOffset() - 1;

		if (start < 0) {
			start++;
		}
		if (end > length)
			end = length;

		String line = document.getText(start, end - start);
		if (!line.startsWith("\n"))
			line = "\n" + line;

		document.insertString(end, line, null);
	}
}
