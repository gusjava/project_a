package a.entity.gus06.appli.gusclient1.gui.appdoc.manager.viewer;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140828";}


	private Service modules1;
	private Service modules2;
	private Service main;

	
	private JTabbedPane tab;
	
	public EntityImpl() throws Exception
	{
		modules1 = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.manager.viewer.modules1");
		modules2 = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.manager.viewer.modules2");
		main = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.manager.viewer.main");
		
		tab = new JTabbedPane();
		tab.addTab("Main classes",(JComponent) main.i());
		tab.addTab("Modules (list)",(JComponent) modules1.i());
		tab.addTab("Modules (tree)",(JComponent) modules2.i());
	}
	
	
	public Object i() throws Exception
	{return tab;}
}
