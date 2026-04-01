package a.entity.gus06.sys.script1.access.context.pool.first;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20150903";}


	private Service getStart;
	private Service getPool;

	public EntityImpl() throws Exception
	{
		getStart = Outside.service(this,"gus06.sys.script1.access.context.execution.start");
		getPool = Outside.service(this,"gus06.sys.script1.access.tag.stack1.pool1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		Map startTag = (Map) getStart.t(context);
		Map pool = (Map) getPool.t(startTag);
		
		return pool;
	}
}
