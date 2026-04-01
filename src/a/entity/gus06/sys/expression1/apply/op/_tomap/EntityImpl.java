package a.entity.gus06.sys.expression1.apply.op._tomap;

import a.framework.*;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.io.File;
import java.util.prefs.Preferences;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160129";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.tomap");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map)		return obj;
		if(obj instanceof Set)		return perform.t(obj);
		if(obj instanceof List)		return perform.t(obj);
		if(obj instanceof Object[])	return perform.t(obj);
		if(obj instanceof R)		return perform.t(obj);
		if(obj instanceof T)		return perform.t(obj);
		if(obj instanceof String)	return perform.t(obj);
		if(obj instanceof File)		return perform.t(obj);
		if(obj instanceof Preferences)	return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
