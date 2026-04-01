package a.entity.gus06.appli.vindinium.session.params;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20170917";}
	
	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
	}

	public void v(String key, Object obj) throws Exception
	{
		map.put(key,obj);
	}

	public Object r(String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
