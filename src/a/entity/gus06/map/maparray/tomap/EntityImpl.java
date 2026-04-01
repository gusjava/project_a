package a.entity.gus06.map.maparray.tomap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151203";}


	public Object t(Object obj) throws Exception
	{
		Map[] mm = (Map[]) obj;
		Map map = new HashMap();
		for(Map m:mm) map.putAll(m);
		return map;
	}
}
