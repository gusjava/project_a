package a.entity.gus06.sys.script1.tool.execute.tag.prepare.stack;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, P {
	
	public String creationDate() {return "20160724";}
	
	// context
	public static final String C_INPUT = "input";
	
	// execution
	public static final String X_START = "start";
	public static final String X_PARENT = "parent";
	public static final String X_SCRIPT = "script";
	
	// tag
	public static final String K_STACK = "stack";
	public static final String K_PARENT = "parent";
	public static final String K_DATA = "data";
	
	// pool
	public static final String P_PARENT = "parent";
	public static final String P_SCRIPT = "script";
	public static final String P_STACK = "stack";
	
	// stack
	public static final String S_CONTEXT = "context";
	public static final String S_OWNER = "owner";
	public static final String S_PARENT = "parent";
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


		Map stack = map("stack");
		
		Map parentTag = (Map) get(tag,K_PARENT);
		boolean isRoot = parentTag==null;
		if(isRoot) parentTag = (Map) get(execution,X_PARENT);
		if(parentTag==null) throw new Exception("Parent not available for tag: "+tag);
		
		Map parentStack = (Map) get(parentTag,K_STACK);
		if(parentStack==null) throw new Exception("Stack not found inside parent tag: "+parentTag+" (parent not executed?)");
		
		Map rootStack = isRoot? stack : (Map) get(parentStack,S_ROOT);
		if(rootStack==null) throw new Exception("Root not found inside stack for parent tag: "+parentTag);
		
		Map parentPool = (Map) get(parentStack,S_POOL);
		if(parentPool==null) throw new Exception("Pool not found inside stack for parent tag: "+parentTag);
		
		Map script = (Map) get(parentStack,S_SCRIPT);
		if(script==null) throw new Exception("Script not found inside stack for parent tag: "+parentTag);
		
		Integer parentLevel = (Integer) get(parentStack,S_LEVEL);
		if(parentLevel==null) throw new Exception("Level not found inside stack for parent tag: "+parentTag);
		
		Integer level = Integer.valueOf(parentLevel.intValue()+1);
		

		Map pool = (Map) buildPool.t(context);
		
		Map data = (Map) get(tag,K_DATA);
		if(data!=null) pool.putAll(data);
		
		tag.put(K_PARENT,parentTag);
		pool.put(P_PARENT,parentPool);
		
		
		stack.put(S_CONTEXT,context);
		stack.put(S_OWNER,tag);
		stack.put(S_POOL,pool);
		stack.put(S_BLOCK1,map("block1"));
		stack.put(S_BLOCK2,map("block2"));
		stack.put(S_PARENT,parentStack);
		stack.put(S_ROOT,rootStack);
		stack.put(S_LEVEL,level);
		
		
		File newScriptFile = (File) getAndRemove(execution,X_SCRIPT);
		if(newScriptFile!=null)
		{
			Map newScript = map("script");
			newScript.put("parent",script);
			newScript.put("root",rootStack);
			newScript.put("file",newScriptFile);
			newScript.put("dir",newScriptFile.getParentFile());
			
			script = newScript;
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
