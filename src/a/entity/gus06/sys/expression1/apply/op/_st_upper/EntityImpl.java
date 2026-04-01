package a.entity.gus06.sys.expression1.apply.op._st_upper;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160502";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(obj instanceof String) return Boolean.valueOf(matches((String) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public boolean matches(String s) throws Exception
	{
		return s.matches("^[A-Z].*");
	}
}
