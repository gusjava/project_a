package a.entity.gus06.appli.gusexplorer.menu.tools;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160909";}

	
	private Service script;
	private Service screen;
	private Service monitor;
	private Service icons;
	private Service colors;
	private Service clipboard;
	private Service web;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		script = Outside.service(this,"gus06.appli.gusexplorer.menu.tools.script");
		screen = Outside.service(this,"gus06.appli.gusexplorer.menu.tools.screen");
		monitor = Outside.service(this,"gus06.appli.gusexplorer.menu.tools.monitor");
		icons = Outside.service(this,"gus06.appli.gusexplorer.menu.tools.icons");
		colors = Outside.service(this,"gus06.appli.gusexplorer.menu.tools.colors");
		clipboard = Outside.service(this,"gus06.appli.gusexplorer.menu.tools.clipboard");
		web = Outside.service(this,"gus06.appli.gusexplorer.menu.tools.web");
		
		JMenu menuScript = (JMenu) script.i();
		JMenu menuScreen = (JMenu) screen.i();
		JMenu menuMonitor = (JMenu) monitor.i();
		JMenu menuIcons = (JMenu) icons.i();
		JMenu menuColors = (JMenu) colors.i();
		JMenu menuClipboard = (JMenu) clipboard.i();
		JMenu menuWeb = (JMenu) web.i();
		
		menu = new JMenu("Tools");
		
		menu.add(menuScript);
		menu.add(menuScreen);
		menu.add(menuMonitor);
		menu.add(menuIcons);
		menu.add(menuColors);
		menu.add(menuClipboard);
		menu.add(menuWeb);
	}
	
	public Object i() throws Exception
	{return menu;}
}