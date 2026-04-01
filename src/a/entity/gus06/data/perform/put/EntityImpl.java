package a.entity.gus06.data.perform.put;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160128";}
	
	
	private Service performFile;
	
	private Service performMap;
	private Service performList;
	private Service performArrayObj;
	private Service performArrayBoolean;
	private Service performArrayDouble;
	private Service performArrayFloat;
	private Service performArrayInt;
	private Service performArrayLong;
	
	
	public EntityImpl() throws Exception
	{
		performFile = Outside.service(this,"gus06.file.properties.perform.field.put");
		
		performMap = Outside.service(this,"gus06.map.put");
		performList = Outside.service(this,"gus06.list.put");
		performArrayObj = Outside.service(this,"gus06.array.objectarray.put");
		performArrayBoolean = Outside.service(this,"gus06.array.booleanarray.put");
		performArrayDouble = Outside.service(this,"gus06.array.doublearray.put");
		performArrayFloat = Outside.service(this,"gus06.array.floatarray.put");
		performArrayInt = Outside.service(this,"gus06.array.intarray.put");
		performArrayLong = Outside.service(this,"gus06.array.longarray.put");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0] instanceof File) {performFile.p(o);return;}
		
		if(o[0] instanceof Map) {performMap.p(o);return;}
		if(o[0] instanceof List) {performList.p(o);return;}
		if(o[0] instanceof Object[]) {performArrayObj.p(o);return;}
		if(o[0] instanceof boolean[]) {performArrayBoolean.p(o);return;}
		if(o[0] instanceof double[]) {performArrayDouble.p(o);return;}
		if(o[0] instanceof float[]) {performArrayFloat.p(o);return;}
		if(o[0] instanceof int[]) {performArrayInt.p(o);return;}
		if(o[0] instanceof long[]) {performArrayLong.p(o);return;}
		
		throw new Exception("Invalid data type: "+o[0].getClass().getName());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		if(o[0] instanceof Map) return performMap.t(o);
		if(o[0] instanceof List) return performList.t(o);
		if(o[0] instanceof Object[]) return performArrayObj.t(o);
		if(o[0] instanceof boolean[]) return performArrayBoolean.t(o);
		if(o[0] instanceof double[]) return performArrayDouble.t(o);
		if(o[0] instanceof float[]) return performArrayFloat.t(o);
		if(o[0] instanceof int[]) return performArrayInt.t(o);
		if(o[0] instanceof long[]) return performArrayLong.t(o);
		
		throw new Exception("Invalid data type: "+o[0].getClass().getName());
	}
}
