package a.entity.gus06.data.perform.findall.random;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170506";}
	
	
	private Service performArray;
	private Service performList;
	private Service performSet;
	private Service performMap;
	
	public EntityImpl() throws Exception
	{
		performArray = Outside.service(this,"gus06.array.objectarray.findall.random");
		performList = Outside.service(this,"gus06.list.findall.random");
		performSet = Outside.service(this,"gus06.set.findall.random");
		performMap = Outside.service(this,"gus06.map.findall.random");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof Object[]) return performArray.t(obj);
		if(input instanceof List) return performList.t(obj);
		if(input instanceof Set) return performSet.t(obj);
		if(input instanceof Map) return performMap.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
