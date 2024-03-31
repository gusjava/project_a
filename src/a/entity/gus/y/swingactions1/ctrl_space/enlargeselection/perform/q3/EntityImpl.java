package a.entity.gus.y.swingactions1.ctrl_space.enlargeselection.perform.q3;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}
	
	private Service performTreeTag;

	public EntityImpl() throws Exception {
		performTreeTag = Outside.service(this, "gus.y.swingactions1.ctrl_space.enlargeselection.perform.q3.treetag");
	}

	public void p(Object obj) throws Exception {
		JTextComponent comp = (JTextComponent) obj;

		String text = comp.getText();
		int start = comp.getSelectionStart();
		int end = comp.getSelectionEnd();

		if (text.charAt(start) == '@' && (start == 0 || text.charAt(start - 1) == '\n')) {
			performTreeTag.p(comp);
			return;
		}

		comp.selectAll();
	}
}