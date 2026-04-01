package a.entity.gus06.jdbc.connection.builder.watcher;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170208";}

	private Service cxBuilder;
	
	public EntityImpl() throws Exception
	{
		cxBuilder = Outside.service(this,"gus06.jdbc.connection.builder");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) cxBuilder.g();
		if(!map.containsKey(obj)) return null;
		return map.get(obj);
	}
}
