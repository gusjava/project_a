package a.entity.gus.y.debug1.print.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20231203";}

	private Map main;
	private boolean debug;
	
	public EntityImpl() throws Exception {
		main = (Map) Outside.resource(this, "main");
		debug = (Boolean) Outside.resource(this, "propbool#debug:false");
	}
	
	public void e() throws Exception {
		if(!debug) return;
		
		List keys = new ArrayList<>(main.keySet());
		Collections.sort(keys);
		for(int i=0;i<keys.size();i++) {
			String key = (String) keys.get(i);
			Object value = main.get(key);
			System.out.println("DEBUG:"+key+"\t"+value.getClass().getName());
		}
	}
}
