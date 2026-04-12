package a.core.gus.gyem.m053.p.logger;

import a.core.gus.gyem.GyemSystem;
import a.core.gus.gyem.utils.UtilLog;
import a.framework.P;

public class Module extends GyemSystem implements P {
	
	public void p(Object obj) throws Exception {
		Object[] infos = (Object[]) obj;
		UtilLog.println(infos[0], (String) infos[1]);
	}
}
