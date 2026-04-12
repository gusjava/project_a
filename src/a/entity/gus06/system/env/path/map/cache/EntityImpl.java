package a.entity.gus06.system.env.path.map.cache;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170313";}

	private Service find;
	private Map map;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.system.env.path.map");
	}
	
	public Object g() throws Exception
	{
		if(map==null) map = (Map) find.g();
		return map;
	}
}
