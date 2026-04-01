package a.entity.gus06.sys.script1.tool.execute.op.set0;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151104";}


	private Service deepPut;
	private Service getPool;
	private Service analyze;

	public EntityImpl() throws Exception
	{
		deepPut = Outside.service(this,"gus06.map.deep.put");
		getPool = Outside.service(this,"gus06.sys.script1.access.context.pool.first");
		analyze = Outside.service(this,"gus06.sys.script1.tool.execute.op.set.analyze");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String line = (String) o[1];
		
		synchronized(context)
		{
			Object[] infos = (Object[]) analyze.t(obj);
			if(infos.length!=3) throw new Exception("Wrong infos number: "+infos.length);
			
			Map pool = (Map) getPool.t(context);
			deepPut.p(new Object[]{pool,infos[0],infos[1],infos[2]});
		}
	}
}
