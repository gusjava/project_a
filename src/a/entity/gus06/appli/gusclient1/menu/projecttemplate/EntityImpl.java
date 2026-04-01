package a.entity.gus06.appli.gusclient1.menu.projecttemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140905";}


	public static final String KEY = "gusclient1_menu_projecttemplate";


	private Service localize;

	private Service a_mainguiPanel;
	private Service a_newAction;
	private Service a_autoconfig1;

	private JMenu menu;

	
	public EntityImpl() throws Exception
	{
		localize = Outside.service(this,"gus06.ling.localize.manager");
	
		a_mainguiPanel = Outside.service(this,"gus06.appli.gusclient1.action.project.template.maingui.panel");
		a_newAction = Outside.service(this,"gus06.appli.gusclient1.action.project.template.newaction");
		a_autoconfig1 = Outside.service(this,"gus06.appli.gusclient1.action.project.template.autoconfig1");
	
		
		menu = new JMenu("Project template");
		localize.v(KEY,menu);
		
		add(a_mainguiPanel);
		add(a_newAction);
		add(a_autoconfig1);
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
