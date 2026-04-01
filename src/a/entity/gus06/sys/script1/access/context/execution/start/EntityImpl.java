package a.entity.gus06.sys.script1.access.context.execution.start;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}
	
	public static final String X_START = "start";


	private Service getExecution;

	public EntityImpl() throws Exception
	{
		getExecution = Outside.service(this,"gus06.sys.script1.access.context.execution");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		Map execution = (Map) getExecution.t(context);
		
		Map start = (Map) get(execution,X_START);
		return start;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
