package a.core.gus.gyem.m034.t.config.icon;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String iconId = (String) obj;
		String path = (String) moduleT(M035_T_CONFIG_ICON_PATH).t(iconId);
		return moduleT(M033_T_READ_ICON).t(path);
	}
}
