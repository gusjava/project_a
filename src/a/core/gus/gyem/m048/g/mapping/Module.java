package a.core.gus.gyem.m048.g.mapping;

import java.util.HashMap;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private Map mapping;

	public Object g() throws Exception {
		if(mapping==null) init();
		return mapping;
	}

	private void init() throws Exception {
		mapping = new HashMap();
		
		Map configMapping = (Map) moduleG(M049_G_CONFIG_MAPPING).g();
		if(configMapping!= null) mapping.putAll(configMapping);
	}
}
