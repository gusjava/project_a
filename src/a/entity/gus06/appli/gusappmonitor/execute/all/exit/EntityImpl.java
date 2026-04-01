package a.entity.gus06.appli.gusappmonitor.execute.all.exit;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190414";}
	
	public static final String COMMAND_EXIT = "exit";


	private Service manager;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusappmonitor.manager");
	}
	
	
	public void e() throws Exception
	{
		List configs = (List) manager.r("configs");
		for(Object config : configs) ((P)config).p(COMMAND_EXIT);
	}
}
