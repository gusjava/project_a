package a.core.gus.gyem.m003.g.prop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private Map prop;

	public Object g() throws Exception {
		if(prop==null) init();
		return prop;
	}
	
	private void init() throws Exception {
		prop = new HashMap();
		
		Map configProp = (Map) moduleG(M004_G_CONFIG_PROP).g();
		if(configProp!=null) prop.putAll(configProp);
		
		Map param = (Map) moduleG(M008_G_PARAM).g();
		Iterator it = param.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			if(key.startsWith("p.")) {
				String value = (String) param.get(key);
				prop.put(key.substring(2), value);
			}
		}
	}
}
