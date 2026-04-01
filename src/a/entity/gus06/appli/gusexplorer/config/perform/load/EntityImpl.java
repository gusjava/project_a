package a.entity.gus06.appli.gusexplorer.config.perform.load;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250907";}

	private Service dataManager;
	private Service configManager;

	public EntityImpl() throws Exception
	{
		dataManager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		configManager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
	}
	
	public void p(Object obj) throws Exception
	{
		String name = (String) obj;
		
		List names = (List) configManager.g();
		if(!names.contains(name))
			throw new Exception("Config name not found: "+name);
			
		names.remove(name);
		names.add(0,name);
		configManager.e();
		
		List list = (List) configManager.r(name);
		dataManager.v("init",list);
	}
}
