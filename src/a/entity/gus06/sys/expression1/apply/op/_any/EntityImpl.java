package a.entity.gus06.sys.expression1.apply.op._any;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}


	private Service buildFilter;
	
	public EntityImpl() throws Exception
	{
		buildFilter = Outside.service(this,"gus06.filter.array.build.any");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer)	return any((Integer) obj);
		if(obj instanceof Long)		return any((Long) obj);
		
		if(obj instanceof List)		return any((List) obj);
		if(obj instanceof Set)		return any((Set) obj);
		if(obj instanceof Map)		return any((Map) obj);
		
		if(obj instanceof boolean[])	return any((boolean[]) obj);
		if(obj instanceof F[])		return buildFilter.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Boolean any(Integer n)
	{
		return Boolean.valueOf(n.intValue()>0);
	}
	private Boolean any(Long n)
	{
		return Boolean.valueOf(n.intValue()>0);
	}
	private Boolean any(List l)
	{
		return Boolean.valueOf(l.size()>0);
	}
	private Boolean any(Set l)
	{
		return Boolean.valueOf(l.size()>0);
	}
	private Boolean any(Map l)
	{
		return Boolean.valueOf(l.size()>0);
	}
	private Boolean any(boolean[] array)
	{
		for(boolean n:array) if(n) return Boolean.TRUE;
		return Boolean.FALSE;
	}
}
