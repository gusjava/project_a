package a.entity.gus06.data.perform.findall.max;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}
	
	
	private Service performList;
	private Service performSet;
	private Service performArray;
	private Service performArray2;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.findall.max");
		performSet = Outside.service(this,"gus06.set.findall.max");
		performArray = Outside.service(this,"gus06.array.objectarray.findall.max");
		performArray2 = Outside.service(this,"gus06.array.d2.objectarray.findall.max");
	}	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)	return performList.t(obj);
		if(input instanceof Set)	return performSet.t(obj);
		if(input instanceof Object[][])	return performArray2.t(obj);
		if(input instanceof Object[])	return performArray.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
