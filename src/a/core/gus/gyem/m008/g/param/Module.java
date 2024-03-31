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
		
		Map configParam = (Map) moduleG(M009_G_CONFIG_PARAM).g();
		if(configParam!=null) param.putAll(configParam);
		
		Map argsParam = (Map) moduleG(M020_G_ARGS_PARAM).g();
		if(argsParam!=null) param.putAll(argsParam);
	}
}
