package a.entity.gus06.appli.gusexplorer.menu.tools.script;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191020";}

	
	private Service draft;
	private Service console;
	private Service remote;
	private Service local;
	private Service tuto;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		draft = Outside.service(this,"gus06.appli.gusexplorer.action.tools.script.draft");
		console = Outside.service(this,"gus06.appli.gusexplorer.action.tools.script.console");
		remote = Outside.service(this,"gus06.appli.gusexplorer.action.tools.script.remote");
		local = Outside.service(this,"gus06.appli.gusexplorer.action.tools.script.local");
		tuto = Outside.service(this,"gus06.appli.gusexplorer.action.tools.tuto.script.extract");
		
		menu = new JMenu("Script");
		
		add(draft);
		add(console);
		add(remote);
		add(local);
		add(tuto);
	}
	
	public Object i() throws Exception
	{return menu;}
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}