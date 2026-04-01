package a.entity.gus06.sys.script1.access.tag.stack1.script1;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160330";}
	
	public static final String S_SCRIPT = "script";


	private Service getStack;

	public EntityImpl() throws Exception
	{
		getStack = Outside.service(this,"gus06.sys.script1.access.tag.stack1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		Map stack = (Map) getStack.t(tag);
		Map script = (Map) get(stack,S_SCRIPT);
		File scriptFile = (File) get(script,"file");
		
		return scriptFile;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
