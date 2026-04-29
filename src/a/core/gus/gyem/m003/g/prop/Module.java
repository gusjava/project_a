package a.core.gus.gyem.m003.g.prop;

import java.util.HashMap;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private Map prop;

	public Object g() throws Exception {
		if(prop==null) init();
		return prop;
	}
	
	private void init() throws Exception {
		prop = new HashMap();

		Map configProp = (Map) moduleG(M004_G_CONFIG_PROP).g();
		if(configProp!=null) prop.putAll(configProp);

		Map outsideFile = (Map) moduleG(M065_G_PROP_OUTSIDE_FILE).g();
		if(outsideFile!=null) prop.putAll(outsideFile);

		Map outsideParams = (Map) moduleG(M066_G_PROP_OUTSIDE_PARAMS).g();
		if(outsideParams!=null) prop.putAll(outsideParams);
	}
}
