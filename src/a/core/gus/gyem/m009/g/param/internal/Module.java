package a.core.gus.gyem.m009.g.param.internal;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		String path = "/" + CORE_NAME;
		return moduleT(M011_T_READ_PROP).t(path);
	}
}
