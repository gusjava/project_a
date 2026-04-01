package a.entity.gus06.data.perform.remove.strict;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170419";}
	
	
	private Service performList;
	private Service performSet;
	private Service performMap;
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.remove.strict");
		performSet = Outside.service(this,"gus06.set.remove.strict");
		performMap = Outside.service(this,"gus06.map.remove.strict");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)
		{performList.p(obj);return;}
		
		if(input instanceof Set)
		{performSet.p(obj);return;}
		
		if(input instanceof Map)
		{performMap.p(obj);return;}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)
			return performList.t(obj);
		
		if(input instanceof Set)
			return performSet.t(obj);
		
		if(input instanceof Map)
			return performMap.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
