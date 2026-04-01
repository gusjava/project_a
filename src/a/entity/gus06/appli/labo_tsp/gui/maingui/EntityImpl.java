package a.entity.gus06.appli.labo_tsp.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190228";}


	private Service tab;
	private Service bottomBar;
	private Service persist;
	
	private Service dataGui;
	private Service computeGui;
	private Service runGui;
	private Service settingGui;
	private Service debugGui;


	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		bottomBar = Outside.service(this,"*gus06.appli.labo_tsp.gui.bottombar");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		dataGui = Outside.service(this,"*gus06.appli.labo_tsp.gui.data");
		computeGui = Outside.service(this,"*gus06.appli.labo_tsp.gui.compute");
		runGui = Outside.service(this,"*gus06.appli.labo_tsp.gui.compute");
		settingGui = Outside.service(this,"*gus06.appli.labo_tsp.gui.settings");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		
		
		
		tab.v("GUI_data#Data",dataGui.i());
		tab.v("GUI_compute#Compute",computeGui.i());
		tab.v("GUI_run#Run",runGui.i());
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
