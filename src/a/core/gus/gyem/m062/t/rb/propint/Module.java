package a.core.gus.gyem.m062.t.rb.propint;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String value = (String) moduleT(M060_T_RB_PROP).t(obj);
		if(value==null) return null;
		return Integer.parseInt(value);
	}
}
