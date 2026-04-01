package a.entity.gus06.sys.script1.access.tag.parent0.stack1.pool1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160415";}


	private Service getParent;
	private Service getPool;

	public EntityImpl() throws Exception
	{
		getParent = Outside.service(this,"gus06.sys.script1.access.tag.parent0");
		getPool = Outside.service(this,"gus06.sys.script1.access.tag.stack1.pool1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		
		Map parentTag = (Map) getParent.t(tag);
		if(parentTag==null) return null;
		return getPool.t(parentTag);
	}
}
