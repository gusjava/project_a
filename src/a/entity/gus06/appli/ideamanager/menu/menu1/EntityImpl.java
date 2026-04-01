package a.entity.gus06.appli.ideamanager.menu.menu1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20151013";}

	private Service fullscreen;
	private Service openDir;
	private Service about;
	private Service exit;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		fullscreen = Outside.service(this,"gus06.app.action.fullscreen.en");
		openDir = Outside.service(this,"gus06.appli.ideamanager.action.open.backupdir");
		about = Outside.service(this,"gus06.app.action.about.en");
		exit = Outside.service(this,"gus06.app.action.exit.en");
	
		menu = new JMenu("Application");
		
		add(openDir);
		add(fullscreen);
		add(about);
		menu.addSeparator();
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
