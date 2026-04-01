package a.entity.gus06.sys.expression1.apply.op._freqmap;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191021";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return buildMap((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Map buildMap(List list)
	{
		Map m = new HashMap();
		for(Object obj : list) append(m,obj);
		return m;
	}
	
	
	private void append(Map m, Object obj)
	{
		if(!m.containsKey(obj))	m.put(obj,1);
		else
		{
			Integer n = (Integer) m.get(obj);
			m.put(obj,n.intValue()+1);
		}
	}
}
