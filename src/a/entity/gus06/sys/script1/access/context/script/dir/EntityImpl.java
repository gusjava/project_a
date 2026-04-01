package a.entity.gus06.sys.script1.access.context.script.dir;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170310";}
	


	private Service contextScript;
	private Service hasCurrent;

	public EntityImpl() throws Exception
	{
		contextScript = Outside.service(this,"gus06.sys.script1.access.context.script");
		hasCurrent = Outside.service(this,"gus06.sys.script1.access.context.execution.current");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		if(!hasCurrent.f(context)) return null;
		
		Map script = (Map) contextScript.t(context);
		File scriptDir = (File) get(script,"dir");
		return scriptDir;
	}
	
	
	private Object get(Map m, String k)
	{return m.containsKey(k)?m.get(k):null;}
}
