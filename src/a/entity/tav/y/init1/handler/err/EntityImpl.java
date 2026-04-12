package a.entity.tav.y.init1.handler.err;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P, V {
	public String creationDate() {return "20240104";}

	private Map main;

	public EntityImpl() throws Exception {
		
	}
	
	public void p(Object obj) throws Exception {
		Object[] infos = (Object[]) obj;
		Entity entity = (Entity) infos[0];
		String id = (String) infos[1];
		Exception e = (Exception) infos[2];
	}
	
	public void v(String key, Object obj) throws Exception {
		if(key.equals("main")) {
			main = (Map) obj;
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
}
