package a.entity.gus06.appli.vindinium.engine.getresponse;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	private Service newEngine;
	private Map map;

	public EntityImpl() throws Exception
	{
		newEngine = Outside.service(this,"gus06.appli.vindinium.engine.buildnew");
		map = new HashMap();
	}


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		String url = (String) t[0];
		Map params = (Map) t[1];
		
		if(map.containsKey(url))
		{
			T engine = (T) map.get(url);
			return engine.t(params);
		}
		
		T engine = (T) newEngine.t(map);
		return engine.t(params);
	}
}
