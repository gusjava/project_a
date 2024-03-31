package a.entity.gus.y.swingactions1.ctrl_d.removeline.perform;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	private Service selectionMono;
	private Service selectionMulti;
	private Service caret;

	public EntityImpl() throws Exception {
		selectionMono = Outside.service(this, "gus.y.swingactions1.ctrl_d.removeline.perform.selection.mono");
		selectionMulti = Outside.service(this, "gus.y.swingactions1.ctrl_d.removeline.perform.selection.multi");
		caret = Outside.service(this, "gus.y.swingactions1.ctrl_d.removeline.perform.caret");
	}

	public void p(Object obj) throws Exception {
		if (obj instanceof JTextArea)
			perform((JTextComponent) obj);
		else
			throw new Exception("Invalid data type: " + obj.getClass().getName());
	}

	private void perform(JTextComponent comp) throws Exception {
		if (hasSelectionMulti(comp)) {
			selectionMulti.p(comp);
			return;
		}
		if (hasSelection(comp)) {
			selectionMono.p(comp);
			return;
		}
		caret.p(comp);
	}

	private boolean hasSelection(JTextComponent comp) {
		String s = comp.getSelectedText();
		return s != null && !s.equals("");
	}

	private boolean hasSelectionMulti(JTextComponent comp) {
		String s = comp.getSelectedText();
		return s != null && s.contains("\n");
	}
}