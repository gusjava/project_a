package a.entity.gus06.sys.expression1.apply.op._nempty;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}


	private Service filter;
	
	public EntityImpl() throws Exception
	{
		filter = Outside.service(this,"gus06.data.filter.isnempty");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return Boolean.valueOf(filter.f(obj));
	}
}