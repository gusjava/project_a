package a.entity.gus06.appli.gusclient1.gui.space.projects;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140806";}


	private Service tabHolder;
	private Service bottomBar;
	private Service tabPersist;
	
	private Service dirExplorer;
	private Service releaseManager;
	private Service resourceManager;
	private Service analyzer;
	
	private JPanel panel;
	
	

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		bottomBar = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.bottombar");
		tabPersist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		dirExplorer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.direxplorer");
		releaseManager = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.releasemanager");
		resourceManager = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.resourcemanager");
		analyzer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.projects.analyzer");
		
		
		tabHolder.v("Explorer",dirExplorer.i());
		tabHolder.v("Analyzer",analyzer.i());
		tabHolder.v("Resource manager",resourceManager.i());
		tabHolder.v("Release manager",releaseManager.i());
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(),BorderLayout.CENTER);
		panel.add((JComponent) bottomBar.i(),BorderLayout.SOUTH);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
}
