package a.entity.gus.y.cust1.rb.param;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231202";}

	private Map params;

	public EntityImpl() throws Exception {
		params = (Map) Outside.resource(this, "params");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String info = (String) o[1];
		return params.containsKey(info) ? params.get(info) : null;
	}
}
