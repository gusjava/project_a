package a.entity.gus06.map.chk.value.notnull;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191226";}

	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			if(value==null) throw new Exception("Null value found for key: "+key);
		}
	}
}
