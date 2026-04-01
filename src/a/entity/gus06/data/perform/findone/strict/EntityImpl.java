package a.entity.gus06.data.perform.findone.strict;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170514";}
	
	
	private Service performList;
	private Service performSet;
	private Service performMap;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.findfirst.strict");
		performSet = Outside.service(this,"gus06.set.findone.strict");
		performMap = Outside.service(this,"gus06.map.key.findone.strict");
	}	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List) return performList.t(obj);
		if(input instanceof Set) return performSet.t(obj);
		if(input instanceof Map) return performMap.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
