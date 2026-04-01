package a.entity.gus06.appli.gusclient1.gui.appdoc.manager;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140818";}

	public static final String ID = "manager";
	

	private Service buildInfoPane;
	private Service viewer;
	
	private JTabbedPane tabbedPane;
	private JComponent infoComp;
	

	public EntityImpl() throws Exception
	{
		buildInfoPane = Outside.service(this,"gus06.appli.gusclient1.gui.space.documentation.build.infopane");
		viewer = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.manager.viewer");
		
		infoComp = (JComponent) buildInfoPane.t(ID);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Explications",infoComp);
		tabbedPane.addTab("Explorer",(JComponent) viewer.i());
	}
	
	
	public Object i() throws Exception
	{return tabbedPane;}
}
