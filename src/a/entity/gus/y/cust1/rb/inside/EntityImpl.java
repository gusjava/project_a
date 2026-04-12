package a.entity.gus.y.cust1.rb.inside;

import a.framework.*;

public class EntityImpl implements Entity, T, R {
	public String creationDate() {return "20260329";}

	private Service inside;

	public EntityImpl() throws Exception {
		inside = Outside.service(this,"gus.y.inside1");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return inside.t(o[1]);
	}
	
	public Object r(String key) throws Exception
	{
		return inside.r(key);
	}
}
