package a.entity.gus06.data.perform.collect3;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.awt.Image;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170326";}
	
	
	private Service performList;
	private Service performImage;
	
	private Service performArray;
	private Service performArrayBoolean;
	private Service performArrayDouble;
	private Service performArrayFloat;
	private Service performArrayInt;
	private Service performArrayLong;
	
	private Service performArray2;
	private Service performArrayBoolean2;
	private Service performArrayDouble2;
	private Service performArrayFloat2;
	private Service performArrayInt2;
	private Service performArrayLong2;
	
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.list.collect3");
		performImage = Outside.service(this,"gus06.awt.bufferedimage.transform.color.collect3");
		
		performArray = Outside.service(this,"gus06.array.objectarray.collect3");
		performArrayBoolean = Outside.service(this,"gus06.array.booleanarray.collect3");
		performArrayDouble = Outside.service(this,"gus06.array.doublearray.collect3");
		performArrayFloat = Outside.service(this,"gus06.array.floatarray.collect3");
		performArrayInt = Outside.service(this,"gus06.array.intarray.collect3");
		performArrayLong = Outside.service(this,"gus06.array.longarray.collect3");
		
		performArray2 = Outside.service(this,"gus06.array.d2.objectarray.collect3");
		performArrayBoolean2 = Outside.service(this,"gus06.array.d2.booleanarray.collect3");
		performArrayDouble2 = Outside.service(this,"gus06.array.d2.doublearray.collect3");
		performArrayFloat2 = Outside.service(this,"gus06.array.d2.floatarray.collect3");
		performArrayInt2 = Outside.service(this,"gus06.array.d2.intarray.collect3");
		performArrayLong2 = Outside.service(this,"gus06.array.d2.longarray.collect3");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)		{performList.p(obj);return;}
		
		if(input instanceof boolean[][]) 	{performArrayBoolean2.p(obj);return;}
		if(input instanceof double[][]) 	{performArrayDouble2.p(obj);return;}
		if(input instanceof int[][]) 		{performArrayInt2.p(obj);return;}
		if(input instanceof long[][]) 		{performArrayLong2.p(obj);return;}
		if(input instanceof float[][])	 	{performArrayFloat2.p(obj);return;}
		if(input instanceof Object[][]) 	{performArray2.p(obj);return;}
		
		if(input instanceof boolean[]) 		{performArrayBoolean.p(obj);return;}
		if(input instanceof double[]) 		{performArrayDouble.p(obj);return;}
		if(input instanceof int[]) 		{performArrayInt.p(obj);return;}
		if(input instanceof long[]) 		{performArrayLong.p(obj);return;}
		if(input instanceof float[])	 	{performArrayFloat.p(obj);return;}
		if(input instanceof Object[]) 		{performArray.p(obj);return;}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)		return performList.t(obj);
		if(input instanceof Image)		return performImage.t(obj);
		
		if(input instanceof boolean[][])	return performArrayBoolean2.t(obj);
		if(input instanceof double[][])		return performArrayDouble2.t(obj);
		if(input instanceof int[][])		return performArrayInt2.t(obj);
		if(input instanceof long[][])		return performArrayLong2.t(obj);
		if(input instanceof float[][])		return performArrayFloat2.t(obj);
		if(input instanceof Object[][])		return performArray2.t(obj);
		
		if(input instanceof boolean[])		return performArrayBoolean.t(obj);
		if(input instanceof double[])		return performArrayDouble.t(obj);
		if(input instanceof int[])		return performArrayInt.t(obj);
		if(input instanceof long[])		return performArrayLong.t(obj);
		if(input instanceof float[])		return performArrayFloat.t(obj);
		if(input instanceof Object[])		return performArray.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
