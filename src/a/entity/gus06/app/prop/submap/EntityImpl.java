package a.entity.gus06.app.prop.submap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140919";}


	private Map prop;
	
	public EntityImpl() throws Exception
	{prop = (Map) Outside.resource(this,"props");}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	
	public Object r(String key) throws Exception
	{
		Map subMap = new HashMap();
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key1 = (String) it.next();
			if(key1.startsWith(key+"."))
			subMap.put(key1.substring(key.length()+1),prop.get(key1));
		}
		return subMap;
	}
}
