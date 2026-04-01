package a.entity.gus06.appli.gusclient1.gui.appdoc.resources.viewer;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140828";}


	private Service iconsViewer;
	private Service lingsViewer;
	private Service appsViewer;

	private JTabbedPane tab;
	
	public EntityImpl() throws Exception
	{
		iconsViewer = Outside.service(this,"gus06.appli.gusclient1.gui.appdoc.resources.viewer.icons");
		lingsViewer = Outside.service(this,"gus06.appli.gusclient1.gui.appdoc.resources.viewer.lings");
		appsViewer = Outside.service(this,"gus06.appli.gusclient1.gui.appdoc.resources.viewer.apps");
		
		tab = new JTabbedPane();
		tab.addTab("Icons",(JComponent) iconsViewer.i());
		tab.addTab("Lings",(JComponent) lingsViewer.i());
		tab.addTab("Apps",(JComponent) appsViewer.i());
	}
	
	
	public Object i() throws Exception
	{return tab;}
}
