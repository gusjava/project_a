package a.entity.gus06.sys.expression1.apply.op._one;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}


	private Service buildFilter;
	
	public EntityImpl() throws Exception
	{
		buildFilter = Outside.service(this,"gus06.filter.array.build.one");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer)	return one((Integer) obj);
		if(obj instanceof Double) 	return one((Double) obj);
		if(obj instanceof Long)		return one((Long) obj);
		
		if(obj instanceof boolean[]) 	return one((boolean[]) obj);
		if(obj instanceof F[]) 		return buildFilter.t(obj);
		
		if(obj instanceof Object[]) 	return one((Object[]) obj);
		if(obj instanceof List) 	return one((List) obj);
		if(obj instanceof Set) 		return one((Set) obj);
		if(obj instanceof Map) 		return one((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Boolean one(Integer n)
	{
		return Boolean.valueOf(n.intValue()==1);
	}
	private Boolean one(Long n)
	{
		return Boolean.valueOf(n.intValue()==1);
	}
	private Boolean one(Double n)
	{
		return Boolean.valueOf(n.doubleValue()==1.0);
	}
	private Boolean one(Object[] l)
	{
		return Boolean.valueOf(l.length==1);
	}
	private Boolean one(List l)
	{
		return Boolean.valueOf(l.size()==1);
	}
	private Boolean one(Set l)
	{
		return Boolean.valueOf(l.size()==1);
	}
	private Boolean one(Map l)
	{
		return Boolean.valueOf(l.size()==1);
	}
	
	
	private Boolean one(boolean[] array)
	{
		int found = 0;
		for(boolean n:array) if(n)
		{
			found++;
			if(found>1) return Boolean.FALSE;
		}
		return Boolean.valueOf(found==1);
	}
}