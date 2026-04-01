package a.entity.gus06.sys.expression1.apply.op._p_to_t2e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160518";}


	private Service wrap;

	public EntityImpl() throws Exception
	{
		wrap = Outside.service(this,"gus06.feature.wrap.p.t2e");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof P) return wrap.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
