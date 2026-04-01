package a.entity.gus06.appli.gusclient1.gui.entitytools;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150823";}

	private Service tabHolder;
	private Service tabPersist;
	
	private Service invalidCalls;
	private Service unusedEntities;
	



	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		tabPersist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		invalidCalls = Outside.service(this,"*gus06.appli.gusclient1.gui.entitytools.invalidcalls");
		unusedEntities = Outside.service(this,"gus06.appli.gusclient1.gui.entitytools.unusedentities");
		
		tabHolder.v("Invalid calls",invalidCalls.i());
		tabHolder.v("Unused entities",unusedEntities.i());
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}
