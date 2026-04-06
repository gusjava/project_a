package a.core.gus.gyem.m059.t.rb.new1;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String key = (String) data[1];
		
		Class c = Class.forName(key);
		return c.getDeclaredConstructor().newInstance();
	}
}
