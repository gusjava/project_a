package a.entity.gus06.sys.expression1.apply.op._t2e_to_p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}


	private Service wrap;

	public EntityImpl() throws Exception
	{
		wrap = Outside.service(this,"gus06.feature.wrap.t2e.p");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof T) return wrap.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
