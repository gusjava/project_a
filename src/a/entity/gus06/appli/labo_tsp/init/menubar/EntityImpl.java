package a.entity.gus06.appli.labo_tsp.init.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20190304";}
	
	
	private Service bar;
	private JMenuBar menuBar;


	public EntityImpl() throws Exception
	{
		bar = Outside.service(this,"gus06.app.mainframe.menubar");
		menuBar = (JMenuBar) bar.i();
		
		add(Outside.service(this,"gus06.appli.labo_tsp.menu.menu1"));
		add(Outside.service(this,"gus06.appli.labo_tsp.menu.menu2"));
		
		menuBar.repaint();
	}
	
	
	
	
	private void add(Service s)
	{
		try
		{
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
