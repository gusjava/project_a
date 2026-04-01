package a.entity.gus06.appli.gusclient1.gui.space.directories;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140730";}


	private Service explorer_user;
	private Service explorer_root;
	private Service explorer_src;
	private Service explorer_resources;
	private Service explorer_java;
	
	private Service tabPersist;
	
	private JTabbedPane tab;
	
	
	public EntityImpl() throws Exception
	{
		explorer_user = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.userhome");
		explorer_root = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.root");
		explorer_src = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.src");
		explorer_resources = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.resource");
		explorer_java = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.java");
		
		tabPersist = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		tab = new JTabbedPane();
		
		tab.addTab("User Home",(JComponent) explorer_user.i());
		tab.addTab("Root dir",(JComponent) explorer_root.i());
		tab.addTab("Source code",(JComponent) explorer_src.i());
		tab.addTab("Resources",(JComponent) explorer_resources.i());
		tab.addTab("Java install",(JComponent) explorer_java.i());
		
		tabPersist.v(getClass().getName()+"_tab",tab);
	}
	
	
	
	public Object i() throws Exception
	{return tab;}
}
