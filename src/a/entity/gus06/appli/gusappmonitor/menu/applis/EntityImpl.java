package a.entity.gus06.appli.gusappmonitor.menu.applis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190414";}

	private Service exitAll;
	private Service restartAll;
	private Service killAll;
	
	private Service openDir;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		exitAll = Outside.service(this,"gus06.appli.gusappmonitor.action.all.exit");
		restartAll = Outside.service(this,"gus06.appli.gusappmonitor.action.all.restart");
		killAll = Outside.service(this,"gus06.appli.gusappmonitor.action.all.kill");
		
		openDir = Outside.service(this,"gus06.appli.gusappmonitor.action.opendir");
	
		menu = new JMenu("Applis");
		
		add(exitAll);
		add(restartAll);
		add(killAll);
		
		menu.addSeparator();
		
		add(openDir);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
