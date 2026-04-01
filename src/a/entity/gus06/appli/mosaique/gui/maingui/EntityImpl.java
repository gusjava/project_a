package a.entity.gus06.appli.mosaique.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20141114";}


	private Service tab;
	private Service bottomBar;
	private Service displayPanel;
	private Service settingPanel;
	private Service appViewer;


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		bottomBar = Outside.service(this,"*gus06.appli.mosaique.gui.bottombar");
		displayPanel = Outside.service(this,"*gus06.appli.mosaique.gui.display");
		settingPanel = Outside.service(this,"*gus06.appli.mosaique.gui.settings");
		appViewer = Outside.service(this,"*gus06.app.jarfile.gui.viewer");
		
		tab.v("GUI_display#Affichage",displayPanel.i());
		tab.v("GUI_settings#Options",settingPanel.i());
		tab.v("GUI_java#Code source",appViewer.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		panel.add((JComponent) bottomBar.i(),BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
