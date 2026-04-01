package a.entity.gus06.appli.gamelaser1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160123";}

	private Service tab;
	private Service bottomBar;
	private Service gameGui;
	private Service settingGui;
	private Service debugGui;
	private Service persist;


	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		
		bottomBar = Outside.service(this,"*gus06.appli.gamelaser1.gui.bottombar");
		gameGui = Outside.service(this,"*gus06.appli.gamelaser1.gui.game");
		settingGui = Outside.service(this,"*gus06.appli.gamelaser1.gui.settings");
		
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		
		tab.v("GUI_game#Game",gameGui.i());
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
