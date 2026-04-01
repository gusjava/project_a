package a.entity.gus06.map.maparray.merge.engine1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160227";}


	private Service addToMap;

	public EntityImpl() throws Exception
	{
		addToMap = Outside.service(this,"gus06.map.maparray.merge.engine1.addtomap");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map[] mm = (Map[]) obj;
		Map map = new HashMap();
		for(Map m:mm) addToMap.p(new Object[]{map,m});
		return map;
	}
}