package a.entity.gus06.appli.gusexplorer.menu.tools.web;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191020";}

	
	private Service showBrowser;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		showBrowser = Outside.service(this,"gus06.appli.gusexplorer.action.tools.web.show.browser");
		menu = new JMenu("Web");
		add(showBrowser);
	}
	
	public Object i() throws Exception
	{return menu;}
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
