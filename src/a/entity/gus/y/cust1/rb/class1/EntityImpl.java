package a.entity.gus.y.cust1.rb.class1;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231203";}
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String info = (String) o[1];
		return Class.forName(info);
	}
}
