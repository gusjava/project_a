package a.core.gus.gyem.m004.g.config.prop;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		String path = (String) moduleG(M005_G_CONFIG_PROP_PATH).g();
		if(path==null) return null;
		return moduleT(M011_T_READ_PROP).t(path);
	}
}
