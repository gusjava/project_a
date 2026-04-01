package a.entity.gus06.appli.gusclient1.gui.appdoc.resources.viewer.apps;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140910";}

	private JTabbedPane tab;
	
	public EntityImpl() throws Exception
	{
		tab = new JTabbedPane();
	}
	
	
	public Object i() throws Exception
	{return tab;}
}
