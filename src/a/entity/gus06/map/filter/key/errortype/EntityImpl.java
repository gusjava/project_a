package a.entity.gus06.map.filter.key.errortype;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201106";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.endsWith(".error")) return true;
		}
		return false;
	}
}