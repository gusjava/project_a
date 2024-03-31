package a.core.gus.gyem.m035.t.config.icon.path;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String iconId = (String) obj;
		String configRoot = (String) moduleG(M006_G_CONFIG_ROOT).g();
		if(configRoot==null) return null;
		return configRoot + LOC_ICON + iconId + ".gif";
	}
}
