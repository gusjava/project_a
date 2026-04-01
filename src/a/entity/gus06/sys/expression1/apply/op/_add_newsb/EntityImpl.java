package a.entity.gus06.sys.expression1.apply.op._add_newsb;

import a.framework.*;
import java.util.List;
import java.util.Set;

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
	
	
	private StringBuffer add(List list)
	{
		StringBuffer sb = new StringBuffer();
		list.add(sb);
		return sb;
	}
	
	private StringBuffer add(Set set)
	{
		StringBuffer sb = new StringBuffer();
		set.add(sb);
		return sb;
	}
}
