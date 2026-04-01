package a.entity.gus06.appli.gusexplorer.menu.tools.monitor;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20220918";}

	
	
	private Service showMonitor;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		showMonitor = Outside.service(this,"gus06.appli.gusexplorer.action.tools.monitor.show");
	
		menu = new JMenu("Monitor");
		
		add(showMonitor);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}