package a.entity.gus06.data.perform.findnearest;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}
	
	
	private Service performList;
	private Service performSet;
	private Service performDir;
	
	private Service performArray;
	private Service performDoubleArray;
	private Service performFloatArray;
	private Service performIntArray;
	private Service performLongArray;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.findnearest");
		performSet = Outside.service(this,"gus06.set.findnearest");
		performDir = Outside.service(this,"gus06.dir.findnearest");
		
		performArray = Outside.service(this,"gus06.array.objectarray.findnearest");
		performDoubleArray = Outside.service(this,"gus06.array.doublearray.findnearest");
		performFloatArray = Outside.service(this,"gus06.array.floatarray.findnearest");
		performIntArray = Outside.service(this,"gus06.array.intarray.findnearest");
		performLongArray = Outside.service(this,"gus06.array.longarray.findnearest");
	}	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List) return performList.t(obj);
		if(input instanceof Set) return performSet.t(obj);
		if(input instanceof File) return performDir.t(obj);
		
		if(input instanceof Object[]) return performArray.t(obj);
		if(input instanceof double[]) return performDoubleArray.t(obj);
		if(input instanceof float[]) return performFloatArray.t(obj);
		if(input instanceof int[]) return performIntArray.t(obj);
		if(input instanceof long[]) return performLongArray.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
