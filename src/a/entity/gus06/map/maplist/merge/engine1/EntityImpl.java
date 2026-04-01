package a.entity.gus06.map.maplist.merge.engine1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220903";}


	private Service addToMap;

	public EntityImpl() throws Exception
	{
		addToMap = Outside.service(this,"gus06.map.maparray.merge.engine1.addtomap");
	}
	
	public Object t(Object obj) throws Exception
	{
		List mm = (List) obj;
		Map map = new HashMap();
		for(Object m:mm) addToMap.p(new Object[]{map,m});
		return map;
	}
}