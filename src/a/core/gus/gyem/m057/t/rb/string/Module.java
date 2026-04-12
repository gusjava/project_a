package a.core.gus.gyem.m057.t.rb.string;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String key = (String) data[1];
		
		return key;
	}
}
