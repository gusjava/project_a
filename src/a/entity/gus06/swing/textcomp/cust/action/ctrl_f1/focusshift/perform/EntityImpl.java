package a.entity.gus06.swing.textcomp.cust.action.ctrl_f1.focusshift.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160827";}

	
	private Service focusManager;
	
	public EntityImpl() throws Exception
	{
		focusManager = Outside.service(this,"gus06.swing.textcomp.textfocus.manager");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		V holder = (V) focusManager.t(comp);
		holder.v("focus","shift");
	}
}
