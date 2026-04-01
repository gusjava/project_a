package a.entity.gus06.data.perform.first;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Collection;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160818";}


	private Service isEmpty;
	
	public EntityImpl() throws Exception
	{
		isEmpty = Outside.service(this,"gus06.data.filter.isempty");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(isEmpty.f(obj)) return null;
		
		if(obj instanceof List) return ((List) obj).get(0);
		if(obj instanceof String) return ""+((String) obj).charAt(0);
		
		if(obj instanceof Object[]) return ((Object[]) obj)[0];
		if(obj instanceof boolean[]) return Boolean.valueOf(((boolean[]) obj)[0]);
		if(obj instanceof byte[]) return Byte.valueOf(((byte[]) obj)[0]);
		if(obj instanceof char[]) return ""+((char[]) obj)[0];
		if(obj instanceof int[]) return Integer.valueOf(((int[]) obj)[0]);
		if(obj instanceof long[]) return Long.valueOf(((long[]) obj)[0]);
		if(obj instanceof double[]) return Double.valueOf(((double[]) obj)[0]);
		if(obj instanceof float[]) return Float.valueOf(((float[]) obj)[0]);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}