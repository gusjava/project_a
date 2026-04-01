package a.entity.gus06.appli.gusclient1.menu.selectedentity.cust;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140801";}

	private Service compCust;
	private Service selection;
	
	public EntityImpl() throws Exception
	{
		compCust = Outside.service(this,"gus06.swing.comp.cust3.enable");
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
	}
	
	
	public void p(Object obj) throws Exception
	{
		compCust.p(new Object[]{obj,selection});
	}
}
