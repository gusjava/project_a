package a.core.gus.gyem.m026.i.buildgui.entity;

import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.I;

public class Module extends GyemSystem implements I {
	
	public Object i() throws Exception {
		Map prop = (Map) moduleG(M003_G_PROP).g();
		if(!prop.containsKey(PROP_APP_MAINGUI)) return null;
		
		String rule = (String) prop.get(PROP_APP_MAINGUI);
		log(this, "Building maingui entity with rule: "+rule);
		
		Object entity = moduleT(M014_T_ENTITY_PROVIDE).t(rule);
		return ((I) entity).i();
	}
}
