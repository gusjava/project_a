package a.entity.gus.z.appli1.maingui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231116";}

	private Service tabPersist;
	private Service tabHolder;
	private Service bottomBar;

	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;
	private Service gui6;
	private Service gui7;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");
		bottomBar = Outside.service(this,"*gus.z.appli1.bottombar");

		gui1 = Outside.service(this,"*gus.z.appli1.gui1.documentation");
		gui2 = Outside.service(this,"*gus.z.appli1.gui2.workspace");
		gui3 = Outside.service(this,"*gus.z.appli1.gui3.sandbox");
		gui4 = Outside.service(this,"*gus.z.appli1.gui4.tools");
		gui5 = Outside.service(this,"*gus.z.appli1.gui5.settings");
		gui6 = Outside.service(this,"*gus.z.appli1.gui6.monitor");
		gui7 = Outside.service(this,"*gus.z.appli1.gui7.jarcontent");
		
		tabHolder.v("UTIL_doc#Documentation", gui1);
		tabHolder.v("FILE_java#Workspace", gui2);
		tabHolder.v("GUI_sandbox#Sandbox", gui3);
		tabHolder.v("GUI_tools#Tools", gui4);
		tabHolder.v("GUI_settings#Settings", gui5);
		tabHolder.v("GUI_monitor#Monitoring", gui6);
		tabHolder.v("FILE_jar_search#JAR content", gui7);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
		panel.add((JComponent) bottomBar.i(), BorderLayout.SOUTH);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
