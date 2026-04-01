package a.entity.gus06.appli.entityanalyze.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150526";}


	private Service tab;
	private Service analyzeGui;
	private Service searchGui;
	private Service settingGui;
	private Service debugGui;
	private Service persist;

	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		analyzeGui = Outside.service(this,"*gus06.appli.entityanalyze.gui.analyze");
		searchGui = Outside.service(this,"*gus06.appli.entityanalyze.gui.search");
		settingGui = Outside.service(this,"*gus06.appli.entityanalyze.gui.settings");
		
		tab.v("GUI_analyze#Analyze",analyzeGui.i());
		tab.v("GUI_search#Search",searchGui.i());
		tab.v("GUI_settings#Settings",settingGui.i());
		tab.v("GUI_debug#Debug",debugGui.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
}
