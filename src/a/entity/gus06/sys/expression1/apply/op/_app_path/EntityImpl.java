package a.entity.gus06.sys.expression1.apply.op._app_path;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160916";}
	

	private Map path;
		
	public EntityImpl() throws Exception
	{
		path = (Map) Outside.resource(this,"path");
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
	{return path.containsKey(key)?path.get(key):null;}
}
