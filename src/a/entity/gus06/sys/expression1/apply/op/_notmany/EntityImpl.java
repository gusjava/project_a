package a.entity.gus06.sys.expression1.apply.op._notmany;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160804";}


	private Service buildFilter;
	
	public EntityImpl() throws Exception
	{
		buildFilter = Outside.service(this,"gus06.filter.array.build.notmany");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer) 	return notMany((Integer) obj);
		if(obj instanceof Long) 	return notMany((Long) obj);
		
		if(obj instanceof List) 	return notMany((List) obj);
		if(obj instanceof Set) 		return notMany((Set) obj);
		if(obj instanceof Map) 		return notMany((Map) obj);
		
		if(obj instanceof boolean[])	return notMany((boolean[]) obj);
		if(obj instanceof F[])		return buildFilter.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Boolean notMany(Integer n)
	{
		return Boolean.valueOf(n.intValue()<2);
	}
	private Boolean notMany(Long n)
	{
		return Boolean.valueOf(n.intValue()<2);
	}
	private Boolean notMany(List l)
	{
		return Boolean.valueOf(l.size()<2);
	}
	private Boolean notMany(Set l)
	{
		return Boolean.valueOf(l.size()<2);
	}
	private Boolean notMany(Map l)
	{
		return Boolean.valueOf(l.size()<2);
	}
	
	
	private Boolean notMany(boolean[] array)
	{
		int found = 0;
		for(boolean n:array) if(n)
		{
			found++;
			if(found>1) return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
