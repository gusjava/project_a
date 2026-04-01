package a.entity.gus06.sys.phys2d.size.manager;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20200516";}


	private Map map_size;

	public EntityImpl() throws Exception
	{
		map_size = new HashMap();
	}


	public Object r(String key) throws Exception
	{
		if(!map_size.containsKey(key)) return null;
		return map_size.get(key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		double[] size = (double[]) obj;
		map_size.put(key,size);
	}
}
