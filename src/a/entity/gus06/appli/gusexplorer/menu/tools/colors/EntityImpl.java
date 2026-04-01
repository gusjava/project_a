package a.entity.gus06.appli.gusexplorer.menu.tools.colors;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20201213";}

	
	
	private Service capture;
	private Service picker;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		capture = Outside.service(this,"gus06.appli.gusexplorer.action.tools.colors.capture");
		picker = Outside.service(this,"gus06.appli.gusexplorer.action.tools.colors.picker");
		
		menu = new JMenu("Colors");
		
		add(capture);
		add(picker);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}