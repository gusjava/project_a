package a.entity.gus06.sys.script1.access.context.pool.latest;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150906";}
	
	
	private Service getCurrent;
	private Service getPool;

	public EntityImpl() throws Exception
	{
		getCurrent = Outside.service(this,"gus06.sys.script1.access.context.execution.current");
		getPool = Outside.service(this,"gus06.sys.script1.access.tag.stack1.pool1");
	}
	

	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		if(context==null) return null;
		
		Map currentTag = (Map) getCurrent.t(context);
		Map pool = (Map) getPool.t(currentTag);
		return pool;
	}
}