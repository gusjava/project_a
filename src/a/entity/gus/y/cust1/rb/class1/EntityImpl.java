package a.entity.gus.y.cust1.rb.class1;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231203";}
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String info = (String) data[1];
		return Class.forName(info);
	}
}
