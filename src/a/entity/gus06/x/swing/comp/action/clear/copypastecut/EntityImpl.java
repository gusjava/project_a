package a.entity.gus06.x.swing.comp.action.clear.copypastecut;

import javax.swing.JComponent;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251113";}

	public static final Action EMPTYACTION = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {}
	};

	public void p(Object obj) throws Exception
	{
		JComponent comp = (JComponent) obj;

		comp.getActionMap().put("copy", EMPTYACTION);
		comp.getActionMap().put("cut", EMPTYACTION);
		comp.getActionMap().put("paste", EMPTYACTION);
	}
}