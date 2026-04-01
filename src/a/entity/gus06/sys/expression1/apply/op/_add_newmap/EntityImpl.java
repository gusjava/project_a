package a.entity.gus06.sys.expression1.apply.op._add_newmap;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161204";}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return add((List) obj);
		if(obj instanceof Set) return add((Set) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Map add(List list)
	{
		Map m = new HashMap();
		list.add(m);
		return m;
	}
	
	private Map add(Set set)
	{
		Map m = new HashMap();
		set.add(m);
		return m;
	}
}
