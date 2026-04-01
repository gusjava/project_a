package a.entity.gus06.swing.textcomp.cust.action.escap.focusback.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151107";}


	private Service focusManager;
	
	public EntityImpl() throws Exception
	{
		focusManager = Outside.service(this,"gus06.swing.textcomp.textfocus.manager");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		E holder = (E) focusManager.t(comp);
		holder.e();
	}
}
