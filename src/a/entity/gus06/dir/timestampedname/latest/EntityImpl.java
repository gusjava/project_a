package a.entity.gus06.dir.timestampedname.latest;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170109";}


	private Service buildMap;


	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.dir.timestampedname.buildmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) buildMap.t(obj);
		if(map.isEmpty()) return null;
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		Object key = keys.get(keys.size()-1);
		
		return map.get(key);
	}
}
