package a.entity.gus06.sys.expression1.apply.op._g2e_to_e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}


	private Service wrap;

	public EntityImpl() throws Exception
	{
		wrap = Outside.service(this,"gus06.feature.wrap.g2e.e");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof G) return wrap.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
