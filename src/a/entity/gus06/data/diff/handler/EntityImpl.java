package a.entity.gus06.data.diff.handler;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221007";}


	private Service handleMap;

	public EntityImpl() throws Exception
	{
		handleMap = Outside.service(this,"*gus06.data.diff.handler.map");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Invalid null value");
		if(obj instanceof Map) return handleMap.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
