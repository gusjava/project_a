package a.core.gus.gyem.m060.t.rb.prop;

import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.F;
import a.framework.T;

public class Module extends GyemSystem implements T, F {
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String key = (String) data[1];
		return find(key);
	}
	
	public boolean f(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String key = (String) data[1];
		String value = find(key);
		
		if(value==null) return false;
		value = value.toLowerCase();
		return value.equals("true") || value.equals("1");
	}
	
	private String find(String key) throws Exception {
		String[] infos = analyzeKey(key);
		Map prop = (Map) moduleG(M003_G_PROP).g();
		
		if(!prop.containsKey(infos[0])) return infos[1];
		return (String) prop.get(infos[0]);
	}
	
	private String[] analyzeKey(String key) {
		if(key.contains(":")) return key.split(":",2);
		return new String[] {key, null};
	}
}
