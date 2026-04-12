package a.entity.gus06.appli.gusexplorer.gui.bottombar.custom;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.Action;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251202";}
	
	public static final String STRUCT = "gui.bottom.actions";
	public static final String KEY_DISPLAY = "gui.bottom.actiondisplay.";


	private Service manager;
	private Service buildAction;
	private Map props;
	
	private List list;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.scripts.bottom.manager");
		buildAction = Outside.service(this,"gus06.swing.action.builder0");
		props = (Map) Outside.resource(this,"props");
		
		list = new ArrayList();
		if(!props.containsKey(STRUCT)) return;
		
		String[] nn = ((String) props.get(STRUCT)).split(";");
		for(String n : nn)
		{
			String display = (String) props.get(KEY_DISPLAY+n);
			E execute = new Execute(n);
			Action action = (Action) buildAction.t(new Object[]{display, execute});
			list.add(action);
		}
	}
	
	public Object g() throws Exception
	{return list;}
	
	
	private class Execute implements E, Runnable
	{
		private String name;
		public Execute(String name) {this.name = name;}
		
		public void e() throws Exception
		{new Thread(this,"THREAD_"+getClass().getName()).start();}
		
		public void run()
		{executeScript(name);}
	}
	
	private void executeScript(String name)
	{
		try{manager.p(name);}
		catch(Exception e)
		{Outside.err(this,"executeScript(String)",e);}
	}
}
