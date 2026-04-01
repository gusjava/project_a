package a.entity.gus06.sys.expression1.apply.op._counter_app;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170320";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.cache.counter");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return find(value);
		if(value instanceof Number) return find(value);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Integer find(Object obj) throws Exception
	{
		return toInt(perform.r(""+obj));
	}
	
	
	private Integer toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return (Integer) obj;
		if(obj instanceof String) return Integer.valueOf((String) obj);
		
		throw new Exception("Invalid output data type: "+obj.getClass().getName());
	}
}
