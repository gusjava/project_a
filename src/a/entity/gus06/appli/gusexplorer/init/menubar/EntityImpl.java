package a.entity.gus06.appli.gusexplorer.init.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20151002";}
	
	
	private Service bar;
	private JMenuBar menuBar;


	public EntityImpl() throws Exception
	{
		bar = Outside.service(this,"gus06.app.mainframe.menubar");
		menuBar = (JMenuBar) bar.i();
		
		add("gus06.appli.gusexplorer.menu.menu1");
		add("gus06.appli.gusexplorer.menu.config");
		add("gus06.appli.gusexplorer.menu.tabs");
		add("gus06.appli.gusexplorer.menu.tools");
		add("gus06.appli.gusexplorer.menu.scripts");
		
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
