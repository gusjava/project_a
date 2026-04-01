package a.entity.gus06.sys.expression1.apply.op._app_param;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}
	

	private Map params;
		
	public EntityImpl() throws Exception
	{
		params = (Map) Outside.resource(this,"params");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String)	return get((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object get(String key)
	{return params.containsKey(key)?params.get(key):null;}
}
