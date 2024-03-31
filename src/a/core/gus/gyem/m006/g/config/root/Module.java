package a.core.gus.gyem.m006.g.config.root;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;
import a.framework.T;

public class Module extends GyemSystem implements G, T {
	
	public Object g() throws Exception {
		String configId = (String) moduleG(M007_G_CONFIG_ID).g();
		return configIdToRoot(configId);
	}
	
	public Object t(Object obj) throws Exception {
		String configId = (String) obj;
		return configIdToRoot(configId);
	}
	
	private String configIdToRoot(String configId) {
		if(configId==null) return null;
		return CONFIGPATH + configId.replace(".","/") + "/";
	}
}
