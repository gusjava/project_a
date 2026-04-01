package a.entity.gus06.appli.gusexplorer.config.perform.remove.path;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250903";}


	private Service manager;
	private Service labelCustManager;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
		labelCustManager = Outside.service(this,"gus06.appli.gusexplorer.labelcust.manager");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = toFile(obj);
		Map map = (Map) manager.r("*");
		
		boolean updated = false;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			List list = (List) map.get(name);
			if(list.contains(file))
			{
				list.remove(file);
				updated = true;
			}
		}
		if(updated) manager.v("init", map);
		labelCustManager.v("removePath", file);
		return updated;
	}
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}