package a.entity.gus06.sys.script1.tool.execute.tag.prepare;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F, P {
	
	public String creationDate() {return "20160328";}
	
	// execution
	public static final String X_HISTORY = "history";
	public static final String X_CURRENT = "current";
	public static final String X_START = "start";
	public static final String X_STOP = "stop";
	public static final String X_RETURN = "return";
	


	private Service buildStack;
	private Service buildStack0;
	private Service getExecution;

	public EntityImpl() throws Exception
	{
		buildStack = Outside.service(this,"gus06.sys.script1.tool.execute.tag.prepare.stack");
		buildStack0 = Outside.service(this,"gus06.sys.script1.tool.execute.tag.prepare.stack0");
		getExecution = Outside.service(this,"gus06.sys.script1.access.context.execution");
	}
	
	
	
	public void p(Object obj) throws Exception
	{f(obj);}

	
	
	public boolean f(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = o[0];
		Map context = o[1];
		
		Map execution = (Map) getExecution.t(context);
		
		List history = (List) get(execution,X_HISTORY);
		if(history==null) throw new Exception("History not found inside execution");
		
		boolean alreadyStarted = execution.containsKey(X_START);
		
		
		if(execution.containsKey(X_STOP)) return false;
		if(execution.containsKey(X_RETURN)) return false;
		
		history.add(tag);
		execution.put(X_CURRENT,tag);
		
		if(!alreadyStarted)
		{
			execution.put(X_START,tag);
			initStack0(tag,context,execution);
		}
		else
		{
			initStack(tag,context,execution);
		}
		return true;
	}
	
	
	
	private void initStack(Map tag, Map context, Map execution) throws Exception
	{buildStack.p(new Map[]{tag,context,execution});}
	
	
	private void initStack0(Map tag, Map context, Map execution) throws Exception
	{buildStack0.p(new Map[]{tag,context,execution});}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
