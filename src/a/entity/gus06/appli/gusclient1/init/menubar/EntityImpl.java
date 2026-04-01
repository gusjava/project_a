package a.entity.gus06.appli.gusclient1.init.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20140718";}
	
	
	private Service bar;
	private JMenuBar menuBar;


	public EntityImpl() throws Exception
	{
		bar = Outside.service(this,"gus06.app.mainframe.menubar");
		menuBar = (JMenuBar) bar.i();
		
		add("gus.appli.gusclient1.menu.about");
		add("gus.appli.gusclient1.menu.gus");
		add("gus.appli.gusclient1.menu.languages");
		add("gus.appli.gusclient1.menu.spaces");
		
		add("gus.appli.gusclient1.menu.space.documentation");
		add("gus.appli.gusclient1.menu.space.monitoring");
		add("gus.appli.gusclient1.menu.space.directories");
		add("gus.appli.gusclient1.menu.space.entities");
		add("gus.appli.gusclient1.menu.space.projects");
		
		menuBar.repaint();
	}
	
	
	
	
	private void add(String entityName)
	{
		try
		{
			Service s = Outside.service(this,entityName);
			JMenu menu = (JMenu) s.i();
			if(menu!=null) menuBar.add(menu);
		}
		catch(Exception e)
		{
			Outside.err(this,"add(String)",e);
			menuBar.add(new JMenu("###"));
		}
	}
}
