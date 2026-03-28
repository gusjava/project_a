package a.core.gus.gyem.m008.g.param;

import java.util.HashMap;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private Map param;
	
	public Object g() throws Exception {
		if(param==null) init();
		return param;
	}
	
	private void init() throws Exception {
		param = new HashMap();
		
		Map paramInternal = (Map) moduleG(M009_G_PARAM_INTERNAL).g();
		if(paramInternal!=null) param.putAll(paramInternal);
		
		Map paramArgs = (Map) moduleG(M010_G_PARAM_ARGS).g();
		if(paramArgs!=null) param.putAll(paramArgs);
	}
}
