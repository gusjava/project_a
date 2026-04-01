package a.entity.gus06.appli.gusexplorer.menu.menu1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20151002";}

	private Service fullscreen;
	private Service setting;
	private Service about;
	private Service help;
	private Service debug;
	private Service restart;
	private Service exit;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		fullscreen = Outside.service(this,"gus06.app.action.fullscreen.en");
		setting = Outside.service(this,"gus06.app.action.setting.en");
		about = Outside.service(this,"gus06.app.action.about.en");
		help = Outside.service(this,"gus06.app.action.help.en");
		debug = Outside.service(this,"gus06.app.action.debug.en");
		restart = Outside.service(this,"gus06.app.action.restart.en");
		exit = Outside.service(this,"gus06.app.action.exit.en");
	
		menu = new JMenu("Application");
		
		add(fullscreen);
		add(setting);
		add(help);
		add(debug);
		add(about);
		menu.addSeparator();
		add(restart);
		add(exit);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}