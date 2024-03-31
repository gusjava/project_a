package a.core.gus.gyem.m009.g.config.param;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		String path = "/" + CORE_NAME;
		return moduleT(M010_T_READ_PROP).t(path);
	}
}
