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
		if(configMapping!=null) mapping.putAll(configMapping);

		Map outsideFile = (Map) moduleG(M067_G_MAPPING_OUTSIDE_FILE).g();
		if(outsideFile!=null) mapping.putAll(outsideFile);

		Map outsideParams = (Map) moduleG(M068_G_MAPPING_OUTSIDE_PARAMS).g();
		if(outsideParams!=null) mapping.putAll(outsideParams);
	}
}
