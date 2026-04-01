package a.entity.gus06.sys.script1.access.tag.stack1.context;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161215";}
	
	public static final String S_CONTEXT = "context";


	private Service getStack;

	public EntityImpl() throws Exception
	{
		getStack = Outside.service(this,"gus06.sys.script1.access.tag.stack1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		Map stack = (Map) getStack.t(tag);
		Map context = (Map) get(stack,S_CONTEXT);
		
		if(context==null) throw new Exception("Context not found inside tag ["+tag+"]");
		return context;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
