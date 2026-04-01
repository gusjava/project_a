package a.entity.gus06.data.diff.handler.map;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221007";}

	private Service perform;
	
	private Map map0;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.map.map2tomap4");}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map1 = (Map) obj;
		if(map0==null)
		{
			map0 = map1;
			return null;
		}
		Object result = perform.t(new Map[]{map0,map1});
		map0 = map1;
		return result;
	}
}