package a.core.gus.gyem.m010.t.read.prop;

import java.io.InputStream;
import java.util.Properties;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String path = (String) obj;
		InputStream is = (InputStream) moduleT(M011_T_READ_INPUTSTREAM).t(path);
		if(is==null) return null;
		
		Properties prop = new Properties();
		prop.load(is);
		is.close();
		
		return prop;
	}
}
