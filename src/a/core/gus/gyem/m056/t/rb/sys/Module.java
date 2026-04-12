package a.core.gus.gyem.m056.t.rb.sys;

import java.io.PrintStream;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	private PrintStream sysout0 = System.out;
	private PrintStream syserr0 = System.err;
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String key = (String) data[1];
		
		if(key.equals("sysprop")) return System.getProperties();
		if(key.equals("sysenv")) return System.getenv();
		if(key.equals("sysout")) return System.out;
		if(key.equals("syserr")) return System.err;
		if(key.equals("sysin")) return System.in;
		if(key.equals("sysout0")) return sysout0;
		if(key.equals("syserr0")) return syserr0;
		
		throw new Exception("Unknown key: "+key);
	}
}
