package a.entity.gus.y.cust1.rb.icon;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231129";}

	private Service iconProvider;

	public EntityImpl() throws Exception {
		iconProvider = Outside.service(this, "gus.y.icons1.provider");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String info = (String) o[1];
		return iconProvider.t(info);
	}
}
