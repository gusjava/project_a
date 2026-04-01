package a.entity.gus06.appli.gusdbmanager.menu.initmenu;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20150613";}

	
	private Service locationMenu;
	private Service action_config_server;
	private Service action_about;
	private Service action_exit;
	
	private Service bar;
	private JMenuBar menuBar;
	
	
	
	public EntityImpl() throws Exception
	{
		locationMenu = Outside.service(this,"gus06.appli.gusdbmanager.location.choosermenu");
		action_config_server = Outside.service(this,"gus06.appli.gusdbmanager.action.config");
		action_about = Outside.service(this,"gus06.app.action.about.en");
		action_exit = Outside.service(this,"gus06.app.action.exit.en");
		bar = Outside.service(this,"gus06.app.mainframe.menubar");
		
		menuBar = (JMenuBar) bar.i();
		
		JMenu menu_location = (JMenu) locationMenu.i();
		JMenu menu_action = new JMenu("Actions");
		JMenu menu_help = new JMenu("?");

		menuBar.add(menu_action);
		menuBar.add(menu_location);
		menuBar.add(menu_help);
		
		
		// MENU ACTIONS
		
		initAction(menu_action,action_config_server);
		menu_action.addSeparator();
		initAction(menu_action,action_exit);
		
		// MENU HELP
		
		initAction(menu_help,action_about);
	}
	
	
	
	
	
	
	
	private void initAction(JMenu menu_, Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a==null) return;
		menu_.add(a);
	}
}
