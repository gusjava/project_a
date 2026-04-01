package a.entity.gus06.appli.entityaccess.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150227";}


	private Service tab;
	private Service bottomBar;
	private Service downloadGui;
	private Service uploadGui;
	private Service settingGui;
	private Service debugGui;
	private Service persist;


	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		bottomBar = Outside.service(this,"*gus06.appli.entityaccess.gui.bottombar");
		downloadGui = Outside.service(this,"*gus06.appli.entityaccess.gui.download");
		uploadGui = Outside.service(this,"*gus06.appli.entityaccess.gui.upload");
		settingGui = Outside.service(this,"*gus06.appli.entityaccess.gui.settings");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		
		tab.v("GUI_download#Download all",downloadGui.i());
		tab.v("GUI_upload#Upload all",uploadGui.i());
		tab.v("GUI_settings#Settings",settingGui.i());
		tab.v("GUI_debug#Debug",debugGui.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
		
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		panel.add((JComponent) bottomBar.i(),BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
