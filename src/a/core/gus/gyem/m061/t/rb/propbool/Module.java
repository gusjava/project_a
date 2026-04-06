package a.core.gus.gyem.m061.t.rb.propbool;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String value = (String) moduleT(M060_T_RB_PROP).t(obj);
		if(value==null) return null;
		
		value = value.toLowerCase();
		return value.equals("true") || value.equals("1");
	}
}
