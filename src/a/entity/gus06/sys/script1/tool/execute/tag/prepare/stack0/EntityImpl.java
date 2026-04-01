package a.entity.gus06.sys.script1.tool.execute.tag.prepare.stack0;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, P {
	
	public String creationDate() {return "20160724";}
	
	// context
	public static final String C_INPUT = "input";
	
	// execution
	public static final String X_SCRIPT = "script";
	
	// tag
	public static final String K_STACK = "stack";
	public static final String K_DATA = "data";
	
	// pool
	public static final String P_SCRIPT = "script";
	public static final String P_STACK = "stack";
	
	// stack
	public static final String S_CONTEXT = "context";
	public static final String S_OWNER = "owner";
	public static final String S_ROOT = "root";
	public static final String S_SCRIPT = "script";
	public static final String S_LEVEL = "level";
	public static final String S_POOL = "pool";
	public static final String S_BLOCK1 = "block1";
	public static final String S_BLOCK2 = "block2";



	private Service buildMap;
	private Service buildPool;


	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.map.map1");
		buildPool = Outside.service(this,"gus06.sys.script1.tool.execute.tag.prepare.pool");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = o[0];
		Map context = o[1];
		Map execution = o[2];
		
		
		Map pool = (Map) buildPool.t(context);
		
		Object input = get(context,C_INPUT);
		if(input!=null && input instanceof Map) pool.putAll((Map) input);
		
		Map data = (Map) get(tag,K_DATA);
		if(data!=null) pool.putAll(data);
		
		
		Map stack = map("stack");
		
		stack.put(S_CONTEXT,context);
		stack.put(S_OWNER,tag);
		stack.put(S_POOL,pool);
		stack.put(S_BLOCK1,map("block1"));
		stack.put(S_BLOCK2,map("block2"));
		
		stack.put(S_ROOT,stack);
		stack.put(S_LEVEL,Integer.valueOf(0));
		
		File scriptFile = (File) getAndRemove(execution,X_SCRIPT);
		
		Map script = map("script");
		script.put("root",stack);
		if(scriptFile!=null)
		{
			script.put("file",scriptFile);
			script.put("dir",scriptFile.getParentFile());
		}
		
		stack.put(S_SCRIPT,script);
		pool.put(P_SCRIPT,script);
		
		tag.put(K_STACK,stack);
		pool.put(P_STACK,stack);
	}
	
	
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
	
	private Map map(String name) throws Exception
	{return (Map) buildMap.t(name);}
	
	private Object getAndRemove(Map map, String key)
	{return map.remove(key);}
}
