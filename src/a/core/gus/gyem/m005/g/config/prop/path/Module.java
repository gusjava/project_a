package a.core.gus.gyem.m005.g.config.prop.path;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		String configRoot = (String) moduleG(M006_G_CONFIG_ROOT).g();
		if(configRoot==null) return null;
		return configRoot+LOC_PROP;
	}
}
