package a.entity.gus06.sys.expression1.apply.op._operator_names;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160311";}

	public static final String T = "constant";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		Set set = new HashSet(opMap.keySet());
		set.remove("__context");
		
		return set;
	}
}
