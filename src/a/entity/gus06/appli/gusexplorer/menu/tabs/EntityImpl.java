package a.entity.gus06.appli.gusexplorer.menu.tabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20151003";}

	private Service closeSelected;
	private Service closeOthers;
	private Service closeAll;
	
	private Service menu_add;
	private Service menu_selected;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		closeSelected = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.close.selected");
		closeOthers = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.close.others");
		closeAll = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.close.all");
		
		menu_add = Outside.service(this,"gus06.appli.gusexplorer.menu.tabs.add");
		menu_selected = Outside.service(this,"gus06.appli.gusexplorer.menu.tabs.selected");
	
		menu = new JMenu("Tabs");
		
		menu.add((JMenu) menu_add.i());
		menu.add((JMenu) menu_selected.i());
		
		menu.addSeparator();
		
		add(closeSelected);
		add(closeOthers);
		add(closeAll);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
