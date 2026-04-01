package a.entity.gus06.dir.timestampedname.timestamps;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170111";}


	private Service buildMap;


	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.dir.timestampedname.buildmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) buildMap.t(obj);
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		return keys;
	}
}
