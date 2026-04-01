package a.entity.gus06.appli.gusexplorer.config.perform.save;

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
		List list = (List) dataManager.g();
		
		configManager.v("persistList", new Object[]{name, list});
		
		names.remove(name);
		names.add(0,name);
		
		configManager.e();
	}
}
