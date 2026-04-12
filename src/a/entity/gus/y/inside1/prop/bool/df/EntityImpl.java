package a.entity.gus.y.inside1.prop.bool.df;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, F {
	public String creationDate() {return "20260330";}
	
	public static final boolean DEFAULT_VALUE = false;

	private Map prop;

	public EntityImpl() throws Exception {
		prop = (Map) Outside.resource(this, "props");
	}

	public boolean f(Object obj) throws Exception {
		String key = (String) obj;
		
		if(!prop.containsKey(key)) return DEFAULT_VALUE;
		
		String value = (String) prop.get(key);
		return value.toLowerCase().equals("true");
	}
}
