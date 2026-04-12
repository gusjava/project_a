package a.entity.gus06.sys.expression1.apply.op._is_string_email;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160611";}


	private Service check;
	
	public EntityImpl() throws Exception
	{check = Outside.service(this,"gus06.filter.string.is.email");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof String)) return Boolean.FALSE;
		
		return Boolean.valueOf(check.f(obj));
	}
}
