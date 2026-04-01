package a.entity.gus06.data.perform.findall;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151119";}
	
	
	private Service performList;
	private Service performSet;
	private Service performMap;
	private Service performDir;
	private Service performArray;
	private Service performDoubleArray;
	private Service performIntArray;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.findall");
		performSet = Outside.service(this,"gus06.set.findall");
		performMap = Outside.service(this,"gus06.map.key.findall");
		performDir = Outside.service(this,"gus06.dir.findall");
		performArray = Outside.service(this,"gus06.array.objectarray.findall");
		performDoubleArray = Outside.service(this,"gus06.array.doublearray.findall");
		performIntArray = Outside.service(this,"gus06.array.intarray.findall");
	}	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List) return performList.t(obj);
		if(input instanceof Set) return performSet.t(obj);
		if(input instanceof Map) return performMap.t(obj);
		if(input instanceof File) return performDir.t(obj);
		if(input instanceof Object[]) return performArray.t(obj);
		if(input instanceof double[]) return performDoubleArray.t(obj);
		if(input instanceof int[]) return performIntArray.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
