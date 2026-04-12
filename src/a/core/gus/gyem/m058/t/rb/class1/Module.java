package a.core.gus.gyem.m058.t.rb.class1;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String key = (String) data[1];
		
		return Class.forName(key);
	}
}
