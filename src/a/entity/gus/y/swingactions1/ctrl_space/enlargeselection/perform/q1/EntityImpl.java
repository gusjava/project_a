package a.entity.gus.y.swingactions1.ctrl_space.enlargeselection.perform.q1;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	private Service performZ1;
	private Service performXml;

	public EntityImpl() throws Exception {
		performZ1 = Outside.service(this, "gus.y.swingactions1.ctrl_space.enlargeselection.perform.q2");
		performXml = Outside.service(this, "gus.y.swingactions1.ctrl_space.enlargeselection.perform.q1.xml");
	}

	public void p(Object obj) throws Exception {
		JTextComponent comp = (JTextComponent) obj;
		String selection = comp.getSelectedText();

		if (selection != null) {
			if (selection.matches("<[^>]+>")) {
				performXml.p(comp);
				return;
			}

		}

		performZ1.p(comp);
	}
}