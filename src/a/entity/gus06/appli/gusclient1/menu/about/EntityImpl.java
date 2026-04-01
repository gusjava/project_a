package a.entity.gus06.appli.gusclient1.menu.about;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140718";}

	private Service console;
	private Service about;
	private Service restart;
	private Service exit;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		console = Outside.service(this,"gus06.appli.gusclient1.action.console");
		about = Outside.service(this,"gus06.app.action.about");
		restart = Outside.service(this,"gus06.app.action.restart");
		exit = Outside.service(this,"gus06.app.action.exit");
	
		menu = new JMenu("?");
		
		add(console);
		add(restart);
		add(exit);
		menu.addSeparator();
		add(about);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
