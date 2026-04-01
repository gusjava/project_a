package a.entity.gus06.sys.script1.access.context.evaluator;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151102";}
	
	public static final String C_EXP_EVALUATOR = "exp_evaluator";


	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		T evaluator = (T) get(context,C_EXP_EVALUATOR);
		return evaluator;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
