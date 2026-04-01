package a.entity.gus06.appli.gusprojectmanager.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150614";}


	private Service tab;
	private Service projectGui;
	private Service iconGui;
	private Service settingGui;
	private Service persist;
	private Service debugGui;


	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		projectGui = Outside.service(this,"*gus06.appli.gusprojectmanager.gui.projects");
		iconGui = Outside.service(this,"*gus06.appli.gusprojectmanager.gui.icons");
		settingGui = Outside.service(this,"*gus06.appli.gusprojectmanager.gui.settings");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		
		tab.v("GUI_projects#Projects",projectGui.i());
		tab.v("GUI_icons#Icons",iconGui.i());
		tab.v("GUI_settings#Settings",settingGui.i());
		tab.v("GUI_debug#Debug",debugGui.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
		
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
