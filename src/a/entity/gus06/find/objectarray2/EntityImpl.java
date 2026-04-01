package a.entity.gus06.find.objectarray2;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180109";}


	private Service fromDoubleArray2;
	private Service fromIntArray2;
	private Service fromBooleanArray2;
	private Service fromLongArray2;
	private Service fromFloatArray2;
	private Service fromByteArray2;
	private Service fromShortArray2;
	private Service fromList;
	
	public EntityImpl() throws Exception
	{
		fromDoubleArray2 = Outside.service(this,"gus06.convert.doublearray2toobjarray2");
		fromIntArray2 = Outside.service(this,"gus06.convert.intarray2toobjarray2");
		fromBooleanArray2 = Outside.service(this,"gus06.convert.booleanarray2toobjarray2");
		fromLongArray2 = Outside.service(this,"gus06.convert.longarray2toobjarray2");
		fromFloatArray2 = Outside.service(this,"gus06.convert.floatarray2toobjarray2");
		fromByteArray2 = Outside.service(this,"gus06.convert.bytearray2toobjarray2");
		fromShortArray2 = Outside.service(this,"gus06.convert.shortarray2toobjarray2");
		fromList = Outside.service(this,"gus06.convert.listtoobjarray2");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[][]) return obj;
		
		if(obj instanceof double[][]) return fromDoubleArray2.t(obj);
		if(obj instanceof int[][]) return fromIntArray2.t(obj);
		if(obj instanceof boolean[][]) return fromBooleanArray2.t(obj);
		if(obj instanceof long[][]) return fromLongArray2.t(obj);
		if(obj instanceof float[][]) return fromFloatArray2.t(obj);
		if(obj instanceof byte[][]) return fromByteArray2.t(obj);
		if(obj instanceof short[][]) return fromShortArray2.t(obj);
		if(obj instanceof List) return fromList.t(obj);
		
		return new Object[][]{{obj}};
	}
}
