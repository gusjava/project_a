package a.entity.gus06.data.perform.collect;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.io.File;
import java.awt.Image;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20151117";}
	
	
	private Service performFile;
	private Service performList;
	private Service performSet;
	private Service performMap;
	private Service performString;
	private Service performImage;
	
	private Service performArray2;
	private Service performDoubleArray2;
	private Service performFloatArray2;
	private Service performIntArray2;
	private Service performLongArray2;
	private Service performBooleanArray2;
	
	private Service performArray;
	private Service performDoubleArray;
	private Service performFloatArray;
	private Service performIntArray;
	private Service performLongArray;
	private Service performBooleanArray;
	
	
	
	public EntityImpl() throws Exception
	{
		performFile = Outside.service(this,"gus06.dirfile.perform.each.transform");
		performList = Outside.service(this,"gus06.list.collect");
		performSet = Outside.service(this,"gus06.set.collect");
		performMap = Outside.service(this,"gus06.map.keyvalue.collect");
		performString = Outside.service(this,"gus06.data.string.collect");
		performImage = Outside.service(this,"gus06.awt.bufferedimage.transform.color.collect");
		
		performArray2 = Outside.service(this,"gus06.array.d2.objectarray.collect");
		performDoubleArray2 = Outside.service(this,"gus06.array.d2.doublearray.collect");
		performFloatArray2 = Outside.service(this,"gus06.array.d2.floatarray.collect");
		performIntArray2 = Outside.service(this,"gus06.array.d2.intarray.collect");
		performLongArray2 = Outside.service(this,"gus06.array.d2.longarray.collect");
		performBooleanArray2 = Outside.service(this,"gus06.array.d2.booleanarray.collect");
		
		performArray = Outside.service(this,"gus06.array.objectarray.collect");
		performDoubleArray = Outside.service(this,"gus06.array.doublearray.collect");
		performFloatArray = Outside.service(this,"gus06.array.floatarray.collect");
		performIntArray = Outside.service(this,"gus06.array.intarray.collect");
		performLongArray = Outside.service(this,"gus06.array.longarray.collect");
		performBooleanArray = Outside.service(this,"gus06.array.booleanarray.collect");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof File)		{performFile.p(obj);return;}
		
		if(input instanceof List)		{performList.p(obj);return;}
		if(input instanceof Set)		{performSet.p(obj);return;}
		if(input instanceof Map) 		{performMap.p(obj);return;}
		if(input instanceof StringBuffer)	{performString.p(obj);return;}
		
		if(input instanceof double[][])		{performDoubleArray2.p(obj);return;}
		if(input instanceof float[][])		{performFloatArray2.p(obj);return;}
		if(input instanceof int[][]) 		{performIntArray2.p(obj);return;}
		if(input instanceof long[][]) 		{performLongArray2.p(obj);return;}
		if(input instanceof boolean[][])	{performBooleanArray2.p(obj);return;}
		if(input instanceof Object[][]) 	{performArray2.p(obj);return;}
		
		if(input instanceof double[]) 		{performDoubleArray.p(obj);return;}
		if(input instanceof float[]) 		{performFloatArray.p(obj);return;}
		if(input instanceof int[]) 		{performIntArray.p(obj);return;}
		if(input instanceof long[])		{performLongArray.p(obj);return;}
		if(input instanceof boolean[])		{performBooleanArray.p(obj);return;}
		if(input instanceof Object[]) 		{performArray.p(obj);return;}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)		return performList.t(obj);
		if(input instanceof Set)		return performSet.t(obj);
		if(input instanceof Map)		return performMap.t(obj);
		if(input instanceof String)		return performString.t(obj);
		if(input instanceof Image)		return performImage.t(obj);
		
		if(input instanceof double[][])		return performDoubleArray2.t(obj);
		if(input instanceof float[][])		return performFloatArray2.t(obj);
		if(input instanceof int[][])		return performIntArray2.t(obj);
		if(input instanceof long[][])		return performLongArray2.t(obj);
		if(input instanceof boolean[][])	return performBooleanArray2.t(obj);
		if(input instanceof Object[][])		return performArray2.t(obj);
		
		if(input instanceof double[])		return performDoubleArray.t(obj);
		if(input instanceof float[])		return performFloatArray.t(obj);
		if(input instanceof int[])		return performIntArray.t(obj);
		if(input instanceof long[])		return performLongArray.t(obj);
		if(input instanceof boolean[])		return performBooleanArray.t(obj);
		if(input instanceof Object[])		return performArray.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
