package a.entity.gus06.sys.expression1.apply.op._add_newset;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

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
	
	
	private Set add(List list)
	{
		Set s = new HashSet();
		list.add(s);
		return s;
	}
	
	private Set add(Set set)
	{
		Set s = new HashSet();
		set.add(s);
		return s;
	}
}
