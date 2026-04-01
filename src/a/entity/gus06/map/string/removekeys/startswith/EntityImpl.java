package a.entity.gus06.map.string.removekeys.startswith;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20200922";}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Map map = (Map) obj;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String k = (String) it.next();
			if(k.startsWith(key)) it.remove();
		}
	}
}
