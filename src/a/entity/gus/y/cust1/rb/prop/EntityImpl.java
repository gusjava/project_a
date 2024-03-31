package a.entity.gus.y.cust1.rb.prop;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231203";}

	private Map prop;

	public EntityImpl() throws Exception {
		prop = (Map) Outside.resource(this, "props");
	}

	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String info = (String) data[1];
		return prop.containsKey(info) ? prop.get(info) : null;
	}
}
