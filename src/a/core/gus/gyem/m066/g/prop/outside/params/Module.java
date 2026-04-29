package a.core.gus.gyem.m066.g.prop.outside.params;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {

	public static final String START = "p.";

	public Object g() throws Exception {
		Map map = new HashMap();
		Map params = (Map) moduleG(M008_G_PARAM).g();
		Iterator it = params.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.startsWith(START))
				map.put(key.substring(START.length()), params.get(key));
		}
		return map;
	}
}
