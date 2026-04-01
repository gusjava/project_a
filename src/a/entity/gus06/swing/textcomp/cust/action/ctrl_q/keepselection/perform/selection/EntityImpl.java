package a.entity.gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.selection;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151105";}
	
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String selection = comp.getSelectedText();
		comp.setText(selection);
	}
}
