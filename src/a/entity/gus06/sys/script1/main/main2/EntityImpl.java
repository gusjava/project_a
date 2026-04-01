package a.entity.gus06.sys.script1.main.main2;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, R, P, V {

	public String creationDate() {return "20160615";}
	
	public static final String X_START = "start";


	private Service engine;
	private Service contextBuilder;
	private Service getPool;
	private Service getExecution;
	
	private Map input;
	private Object output;
	
	private Map context;
	private Object lastSrc;
	
	
	
	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.script1.engine");
		contextBuilder = Outside.service(this,"gus06.sys.script1.context.builder1");
		getPool = Outside.service(this,"gus06.sys.script1.access.tag.stack1.pool1");
		getExecution = Outside.service(this,"gus06.sys.script1.access.context.execution");
		
		input = new HashMap();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("output")) {init(obj);return;}
		if(key.equals("reset")) {reset();return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("input")) return input;
		if(key.equals("output")) return output;
		if(key.equals("context")) return context;
		if(key.equals("lastSrc")) return lastSrc;
		
		if(key.equals("keys")) return new String[]{"input","output","context","lastSrc"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void init(Object output) throws Exception
	{
		this.output = output;
		context = (Map) contextBuilder.t(new Object[]{input,output});
		lastSrc = null;
	}
	
	private void reset() throws Exception
	{
		input.clear();
		context = (Map) contextBuilder.t(new Object[]{input,output});
		lastSrc = null;
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(context==null) throw new Exception("Context not initialized yet");
		
		try
		{
			engine.p(new Object[]{obj,context});
		}
		finally{cleanContext();}
		
		lastSrc = obj;
		performed();
	}
	
	
	private void performed()
	{send(this,"performed()");}
	
	
	
	
	private void cleanContext() throws Exception
	{
		Map execution = (Map) getExecution.t(context);
		Map tag = (Map) execution.remove(X_START);
		Map pool = (Map) getPool.t(tag);
		
		input.clear();
		input.putAll(pool);
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
