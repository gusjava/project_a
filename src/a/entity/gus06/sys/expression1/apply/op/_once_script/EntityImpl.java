package a.entity.gus06.sys.expression1.apply.op._once_script;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170320";}


	private Service perform;
	private Service contextId;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.cache.once");
		contextId = Outside.service(this,"gus06.sys.script1.access.opmap.contextid");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return check(value,opMap);
		if(value instanceof Number) return check(value,opMap);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Boolean check(Object obj, Map opMap) throws Exception
	{
		String id = (String) contextId.t(opMap);
		return Boolean.valueOf(perform.f(id+"_"+obj));
	}
}
