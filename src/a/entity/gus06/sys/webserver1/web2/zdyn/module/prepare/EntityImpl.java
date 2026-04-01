package a.entity.gus06.sys.webserver1.web2.zdyn.module.prepare;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140930";}
	
	
	private Service module_config;
	private Service module_dirs;
	private Service module_resources;

	public EntityImpl() throws Exception
	{
		module_config = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.module.config");
		module_dirs = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.module.dirs");
	}
	
	
	public void p(Object obj) throws Exception
	{
		R mr = (R) obj;
		V mv = (V) obj;
		
		Object access = mr.r("session access");
		
		Map paramsGet = (Map) mr.r("params_get");
		Map paramsPost = (Map) mr.r("params_post");
		Map params = merge(paramsPost,paramsGet);
		
		String space = getAccess(params,access,"space","space");
		String site = getAccess(params,access,"site","site");
		
		Map dirs = (Map) module_dirs.t(new String[]{space,site});
		R config = (R) module_config.t(dirs);
		
		Map data = (Map) mr.r("data");
		
		data.put("space",space);
		data.put("site",site);
		
		data.put("dirs",dirs);
		data.put("config",config);
		data.put("d",new HashMap());
	}
	
	
	
	
	private String getAccess(Map m, Object o, String key, String defaultValue) throws Exception
	{
		if(m.containsKey(key))
		{
			String value = (String) m.get(key);
			((V)o).v(key,value);
			return value;
		}
		if(((F)o).f(key))
		{
			return (String) ((R)o).r(key);
		}
		((V)o).v(key,defaultValue);
		return defaultValue;
	}
	
	
	
	
	
	private Map merge(Map m1, Map m2)
	{
		Map m = new HashMap(m1);
		m.putAll(m2);
		return m;
	}
}
