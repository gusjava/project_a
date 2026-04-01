package a.entity.gus06.appli.gusappmonitor.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190311";}


	private Service server;
	private Service launcher;
	private Service tab;
	private Service persist;
	
	private Service applisGui;
	private Service consoleGui;
	private Service debugGui;


	private JPanel panel;


	public EntityImpl() throws Exception
	{
		server = Outside.service(this,"gus06.appli.gusappmonitor.server");
		launcher = Outside.service(this,"gus06.appli.gusappmonitor.launcher");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		persist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		applisGui = Outside.service(this,"gus06.appli.gusappmonitor.gui.applis");
		consoleGui = Outside.service(this,"gus06.appli.gusappmonitor.gui.console");
		debugGui = Outside.service(this,"*gus06.debug.gui.maingui");
		
		tab.v("Applis",applisGui.i());
		tab.v("Console",consoleGui.i());
		tab.v("Debug",debugGui.i());
		
		persist.v(getClass().getName()+"_tab",tab.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
