package a.entity.gus06.sys.script1.context.builder1.a.app;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151122";}

	private Service buildMap;
	
	private Map main;
	private Map map0;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.map.map1");
		main = (Map) Outside.resource(this,"main");
		
		map0 = new HashMap();
		
		map0.put("sysprop",System.getProperties());
		map0.put("sysenv",System.getenv());
		map0.put("sysout",System.out);
		map0.put("syserr",System.err);
		map0.put("main",main);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		
		Map map = (Map) buildMap.t("app");
		map.putAll(map0);
		
		return map;
	}
}
