package a.entity.gus06.appli.sample1.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;

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
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		mainGui = Outside.service(this,"*gus06.appli.sample1.gui.gui1");
		settingGui = Outside.service(this,"*gus06.appli.sample1.gui.settings");
		
		tab.v("GUI_main#Main",mainGui.i());
		tab.v("GUI_settings#Settings",settingGui.i());
		tab.v("GUI_debug#Debug",debugGui.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
}
