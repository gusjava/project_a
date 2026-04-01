package a.entity.gus06.sys.script1.access.context.execution.current;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20170220";}
	
	public static final String X_CURRENT = "current";


	private Service getExecution;

	public EntityImpl() throws Exception
	{
		getExecution = Outside.service(this,"gus06.sys.script1.access.context.execution");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		Map execution = (Map) getExecution.t(context);
		
		Map current = (Map) get(execution,X_CURRENT);
		return current;
	}
	
	public boolean f(Object obj) throws Exception
	{
		return t(obj)!=null;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
