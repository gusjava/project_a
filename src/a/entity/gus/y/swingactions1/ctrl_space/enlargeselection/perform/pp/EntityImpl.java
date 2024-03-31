package a.entity.gus.y.swingactions1.ctrl_space.enlargeselection.perform.pp;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	public void p(Object obj) throws Exception {
		JTextComponent comp = (JTextComponent) obj;
		int caret = comp.getCaretPosition();
		comp.select(caret, caret);
	}
}
