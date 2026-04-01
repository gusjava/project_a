package a.entity.gus06.appli.labo_tsp.menu.menu2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190306";}

	private Service new1;
	private Service load;
	private Service save;
	private Service exit;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		new1 = Outside.service(this,"gus06.appli.labo_tsp.action.newdata");
		load = Outside.service(this,"gus06.appli.labo_tsp.action.file.load");
		save = Outside.service(this,"gus06.appli.labo_tsp.action.file.save");
	
		menu = new JMenu("Data");
		
		add(new1);
		add(load);
		add(save);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
