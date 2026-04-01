package a.entity.gus06.sys.expert1.analyze.bool.useless;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160810";}


	private Service eval;

	public EntityImpl() throws Exception
	{
		eval = Outside.service(this,"gus06.sys.parser3.tool.evalwith.a");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		Map pool = (Map) m.get("pool");
		String exp = (String) m.get("exp");
		Map opMap = (Map) m.get("opMap");
		Map output = (Map) m.get("output");
		Boolean value = (Boolean) m.get("value");
		
		Object r0 = eval.t(new Object[]{exp,opMap,output});
		if(r0==null) return false;
		
		if(!(r0 instanceof Boolean))
			throw new Exception("Expected value and evaluated value don't have the same type: Boolean & "+r0.getClass().getName());
		if(!r0.equals(value))
			throw new Exception("Expected value and evaluated value are not the same: "+value+" & "+r0);
		
		pool.remove(exp);
		return true;
	}
}
