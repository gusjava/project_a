package a.entity.gus06.data.perform.col;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180116";}


	private Service performArray2;
	private Service performArrayBoolean2;
	private Service performArrayDouble2;
	private Service performArrayFloat2;
	private Service performArrayInt2;
	private Service performArrayLong2;
	private Service performArrayChar2;
	private Service performArrayByte2;
	private Service performArrayShort2;
	
	public EntityImpl() throws Exception
	{
		performArray2 = Outside.service(this,"gus06.array.d2.objectarray.col");
		performArrayBoolean2 = Outside.service(this,"gus06.array.d2.booleanarray.col");
		performArrayDouble2 = Outside.service(this,"gus06.array.d2.doublearray.col");
		performArrayFloat2 = Outside.service(this,"gus06.array.d2.floatarray.col");
		performArrayInt2 = Outside.service(this,"gus06.array.d2.intarray.col");
		performArrayLong2 = Outside.service(this,"gus06.array.d2.longarray.col");
		performArrayChar2 = Outside.service(this,"gus06.array.d2.chararray.col");
		performArrayByte2 = Outside.service(this,"gus06.array.d2.bytearray.col");
		performArrayShort2 = Outside.service(this,"gus06.array.d2.shortarray.col");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		
		if(data instanceof Object[][])		return performArray2.t(obj);
		if(data instanceof double[][])		return performArrayDouble2.t(obj);
		if(data instanceof int[][])		return performArrayInt2.t(obj);
		if(data instanceof long[][])		return performArrayLong2.t(obj);
		if(data instanceof float[][])		return performArrayFloat2.t(obj);
		if(data instanceof boolean[][])		return performArrayBoolean2.t(obj);
		if(data instanceof char[][])		return performArrayChar2.t(obj);
		if(data instanceof byte[][])		return performArrayByte2.t(obj);
		if(data instanceof short[][])		return performArrayShort2.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
