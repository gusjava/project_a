package a.entity.gus06.sys.script1.tool.execute.handle.execute1;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161215";}
	


	private Service getParentPool;
	private Service getContext;
	private Service executeTag;

	public EntityImpl() throws Exception
	{
		getParentPool = Outside.service(this,"gus06.sys.script1.access.tag.parent0.stack1.pool1");
		getContext = Outside.service(this,"gus06.sys.script1.access.tag.stack1.context");
		executeTag = Outside.service(this,"gus06.sys.script1.tool.execute.tag");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = (Map) o[0];
		String executeKey = (String) o[1];
		
		Map context = (Map) getContext.t(tag);
		Map pool = (Map) getParentPool.t(tag);
		
		pool.put(executeKey,new E1(tag,context));
	}
	
	
	
	private class E1 implements E
	{
		private Map tag;
		private Map context;
		
		public E1(Map tag, Map context)
		{
			this.tag = tag;
			this.context = context;
		}
		
		public void e() throws Exception
		{
			executeTag.p(new Map[]{tag,context});
		}
	}
}
