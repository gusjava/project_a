package a.entity.gus06.swing.comp.action.clearcopypastecut;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140927";}



	public static final Action EMPTYACTION = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {}
	};
	
	
	public void p(Object obj) throws Exception
	{
		JComponent comp = (JComponent) obj;
		
		comp.getActionMap().put("copy",EMPTYACTION);
		comp.getActionMap().put("cut",EMPTYACTION);
		comp.getActionMap().put("paste",EMPTYACTION);
	}
}
