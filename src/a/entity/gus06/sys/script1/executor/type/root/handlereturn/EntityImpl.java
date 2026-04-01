package a.entity.gus06.sys.script1.executor.type.root.handlereturn;

import a.framework.*;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160415";}
	
	public static final String X_RETURN = "return";
	


	private Service getParentPool;
	private Service getExecution;

	public EntityImpl() throws Exception
	{
		getParentPool = Outside.service(this,"gus06.sys.script1.access.tag.parent0.stack1.pool1");
		getExecution = Outside.service(this,"gus06.sys.script1.access.context.execution");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = o[0];
		Map context = o[1];
		
		Map pool = (Map) getParentPool.t(tag);
		if(pool==null) return;
		
		Map execution = (Map) getExecution.t(context);
		if(!execution.containsKey(X_RETURN)) return;
		
		Set returnSet = (Set) execution.remove(X_RETURN);
		if(returnSet.isEmpty()) return;
		
		Object returnValue = returnSet.iterator().next();
		pool.put(X_RETURN,returnValue);
	}
}
