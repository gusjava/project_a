package a.entity.gus06.appli.gusclient1.action.entity.cust.enable;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140731";}

	public static final String DISPLAY = "Show console";


	private Service actionCust;
	private Service selection;

	public EntityImpl() throws Exception
	{
		actionCust = Outside.service(this,"gus06.swing.action.cust.enable");
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
	}
	
	
	public void p(Object obj) throws Exception
	{actionCust.p(new Object[]{obj,selection});}
}
