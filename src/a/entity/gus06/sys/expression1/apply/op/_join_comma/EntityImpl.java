package a.entity.gus06.sys.expression1.apply.op._join_comma;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160316";}

	public static final String GLUE = ",";


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.join");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String)		return join(obj);
		if(obj instanceof List)			return join(obj);
		if(obj instanceof Set)			return join(obj);
		if(obj instanceof Map)			return join(obj);
		
		if(obj instanceof Object[])		return join(obj);
		if(obj instanceof int[])		return join(obj);
		if(obj instanceof long[])		return join(obj);
		if(obj instanceof boolean[])		return join(obj);
		if(obj instanceof double[])		return join(obj);
		if(obj instanceof float[])		return join(obj);
		if(obj instanceof char[])		return join(obj);
		if(obj instanceof short[])		return join(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object join(Object obj) throws Exception
	{return perform.t(new Object[]{obj,GLUE});}
}