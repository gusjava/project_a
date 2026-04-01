package a.entity.gus06.appli.gusappmonitor.execute.all.kill;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190414";}


	private Service manager;
	private Service kill;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusappmonitor.manager");
		kill = Outside.service(this,"gus06.appli.gusappmonitor.execute.app.kill");
	}
	
	
	public void e() throws Exception
	{
		List configs = (List) manager.r("configs");
		for(Object config : configs) kill.p(config);
	}
}
