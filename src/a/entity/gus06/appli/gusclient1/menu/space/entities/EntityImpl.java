package a.entity.gus06.appli.gusclient1.menu.space.entities;

import java.util.List;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140812";}

	public static final String SPACE = "entities";
	public static final String LINGKEY = "gusclient1_space_"+SPACE;


	
	private Service localize;
	private Service custMenu;
	
	private Service newEntity;
	private Service backup;
	private Service menu_selected;
	private Service menu_tools;
	private Service menu_pseudo;
	
	private JMenu menu;
	

	public EntityImpl() throws Exception
	{
		localize = Outside.service(this,"gus06.ling.localize.manager");
		custMenu = Outside.service(this,"gus06.appli.gusclient1.menu.space.cust");
		
		newEntity = Outside.service(this,"gus06.appli.gusclient1.action.space.entities.newentity");
		backup = Outside.service(this,"gus06.appli.gusclient1.action.space.entities.backup");
		menu_selected = Outside.service(this,"gus06.appli.gusclient1.menu.selectedentity");
		menu_tools = Outside.service(this,"gus06.appli.gusclient1.menu.tools");
		menu_pseudo = Outside.service(this,"gus06.appli.gusclient1.menu.pseudo");
		
		menu = new JMenu("Entities");
		localize.v(LINGKEY,menu);
		custMenu.v(SPACE,menu);
		
		addAction(newEntity);
		addAction(backup);
		addMenu(menu_selected);
		addMenu(menu_tools);
		addMenu(menu_pseudo);
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	private void addMenu(Service s) throws Exception
	{
		JMenu m = (JMenu) s.i();
		if(m!=null) menu.add(m);
	}
	
	
	public void addAction(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
