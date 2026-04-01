package a.entity.gus06.appli.quartogame.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191115";}


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
		bottomBar = Outside.service(this,"*gus06.appli.quartogame.gui.bottombar");
		gameGui = Outside.service(this,"*gus06.appli.quartogame.gui.game");
		settingGui = Outside.service(this,"*gus06.appli.quartogame.gui.settings");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		
		tab.v("GUI_game#Board",gameGui.i());
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
