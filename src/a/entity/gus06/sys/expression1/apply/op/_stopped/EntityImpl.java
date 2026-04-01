package a.entity.gus06.sys.expression1.apply.op._stopped;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170819";}
	
	public static final String X_CURRENT = "current";
	public static final String X_STOP = "stop";
	
	private Service getExecution;
	private Service getContext;
	
	public EntityImpl() throws Exception
	{
		getExecution = Outside.service(this,"gus06.sys.script1.access.context.execution");
		getContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		Map context = (Map) getContext.t(opMap);
		Map execution = (Map) getExecution.t(context);
		boolean stopped = execution.containsKey(X_STOP);
			
		return Boolean.valueOf(stopped);
	}
}
