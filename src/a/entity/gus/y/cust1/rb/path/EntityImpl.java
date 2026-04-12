package a.entity.gus.y.cust1.rb.path;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231129";}

	private Service provider;

	public EntityImpl() throws Exception {
		provider = Outside.service(this, "gus.y.paths1.provider.main");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String info = (String) o[1];
		return provider.r(info);
	}
}
