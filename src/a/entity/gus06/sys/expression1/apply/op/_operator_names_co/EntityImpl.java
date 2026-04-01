package a.entity.gus06.sys.expression1.apply.op._operator_names_co;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160729";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof String) return build(opMap,(String) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private List build(Map opMap, String s)
	{
		s = s.toLowerCase();
		
		List list = new ArrayList();
		Iterator it = opMap.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			if(name.contains(s)) list.add(name);
		}
		Collections.sort(list);
		return list;
	}
}
