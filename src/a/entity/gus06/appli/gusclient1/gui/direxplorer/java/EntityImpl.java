package a.entity.gus06.appli.gusclient1.gui.direxplorer.java;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140902";}


	private Service explorer_jre;
	private Service explorer_jdks;
	
	private JTabbedPane tab;
	
	
	public EntityImpl() throws Exception
	{
		explorer_jre = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.java.home");
		explorer_jdks = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.java.jdks");
		
		tab = new JTabbedPane();
		
		tab.addTab("JRE Home",(JComponent) explorer_jre.i());
		tab.addTab("JDK Dirs",(JComponent) explorer_jdks.i());
	}
	
	
	
	public Object i() throws Exception
	{return tab;}
}
