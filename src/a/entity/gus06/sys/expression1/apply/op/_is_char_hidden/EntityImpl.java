package a.entity.gus06.sys.expression1.apply.op._is_char_hidden;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190316";}


	private Service check;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.string.hiddenchars.couriernew.asbool");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof String)) return Boolean.FALSE;
		
		String s = (String) obj;
		if(s.length()!=1) return Boolean.FALSE;
		
		return Boolean.valueOf(check.f(s));
	}
}
