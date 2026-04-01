package a.entity.gus06.data.perform.countall;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151119";}
	
	
	private Service performList;
	private Service performSet;
	private Service performMap;
	private Service performArray;
	private Service performArray2;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.countall");
		performSet = Outside.service(this,"gus06.set.countall");
		performMap = Outside.service(this,"gus06.map.value.countall");
		performArray = Outside.service(this,"gus06.array.objectarray.countall");
		performArray2 = Outside.service(this,"gus06.array.d2.objectarray.countall");
	}	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)		return performList.t(obj);
		if(input instanceof Set)		return performSet.t(obj);
		if(input instanceof Map)		return performMap.t(obj);
		if(input instanceof Object[][])		return performArray2.t(obj);
		if(input instanceof Object[])		return performArray.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
