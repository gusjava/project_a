package a.entity.gus06.map.setmap.keepmulti;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150409";}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Set set = (Set) map.get(it.next());
			if(set.size()<2) it.remove();
		}
	}
}
