package a.entity.gus06.swing.textcomp.cust.action.ctrl_f2.autocopyshift.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190804";}

	
	private Service autoCopyManager;
	
	public EntityImpl() throws Exception
	{
		autoCopyManager = Outside.service(this,"gus06.swing.textcomp.autocopy.manager");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		V holder = (V) autoCopyManager.t(comp);
		holder.v("autoCopy","shift");
	}
}
