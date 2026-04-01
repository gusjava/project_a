package a.entity.gus06.appli.convertisseurgus05.gui.maingui;

import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150525";}


	private Service tab;
	private Service mainGui;
	private Service settingGui;
	private Service debugGui;
	private Service persist;

	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		
		mainGui = Outside.service(this,"*gus06.appli.convertisseurgus05.gui.gui1");
		settingGui = Outside.service(this,"*gus06.appli.convertisseurgus05.gui.settings");
		
		tab.v("GUI_search#Search for entities",mainGui.i());
		tab.v("GUI_settings#Settings",settingGui.i());
		tab.v("GUI_debug#Debug",debugGui.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
}
