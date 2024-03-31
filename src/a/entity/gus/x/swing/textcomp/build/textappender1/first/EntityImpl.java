package a.entity.gus.x.swing.textcomp.build.textappender1.first;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240111";}
	
	public Object t(Object obj) throws Exception {
		return new TextAppender((JTextComponent) obj);
	}

	private class TextAppender implements P {
		private JTextComponent comp;

		public TextAppender(JTextComponent comp) {
			this.comp = comp;
		}

		public void p(Object obj) throws Exception {
			String text = (String) obj;
			
			Document doc = comp.getDocument();
			doc.insertString(0, text, null);
			comp.setCaretPosition(0);
		}
	}
}
