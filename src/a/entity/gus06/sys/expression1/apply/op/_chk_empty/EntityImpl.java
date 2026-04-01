package a.entity.gus06.sys.expression1.apply.op._chk_empty;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180424";}
	
	
	private Service filter;
	
	public EntityImpl() throws Exception
	{
		filter = Outside.service(this,"gus06.data.filter.isempty");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(!filter.f(obj)) throw new Exception("Object is expected to be empty: "+obj);
		return obj;
	}
}
