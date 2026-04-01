package a.entity.gus06.sys.script1.context.evaluate;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151031";}


	private Service retrievePool;
	private Service retrieveEvaluator;
	private Service retrieveOpMap;
	
	public EntityImpl() throws Exception
	{
		retrievePool = Outside.service(this,"gus06.sys.script1.access.context.pool.latest");
		retrieveEvaluator = Outside.service(this,"gus06.sys.script1.access.context.evaluator");
		retrieveOpMap = Outside.service(this,"gus06.sys.script1.access.context.opmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String exp = (String) o[1];
		
		Map pool = (Map) retrievePool.t(context);
		T evaluator = (T) retrieveEvaluator.t(context);
		Map opMap = (Map) retrieveOpMap.t(context);
		
		return evaluator.t(new Object[]{pool,opMap,exp});
	}
}
