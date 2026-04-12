package a.entity.tav.y.init1.builder.resource;

import java.util.Map;

import a.framework.Entity;
import a.framework.T;
import a.framework.V;

public class EntityImpl implements Entity, T, V {
	public String creationDate() {return "20240104";}
	
	private Map main;

	public EntityImpl() throws Exception {
		
	}
	
	public Object t(Object obj) throws Exception {
		Object[] infos = (Object[]) obj;
		Entity entity = (Entity) infos[0];
		String id = (String) infos[1];
		
		return null;
	}
	
	public void v(String key, Object obj) throws Exception {
		if(key.equals("main")) {
			main = (Map) obj;
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
}
