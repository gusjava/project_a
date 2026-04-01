package a.entity.gus06.sys.expression1.apply.op._is_leapyear;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}
	
	
	private Service check;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.time.year.check.leapyear");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		
		if(obj instanceof Integer) return check(obj);
		if(obj instanceof String) return check(obj);
		if(obj instanceof Date) return check(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Boolean check(Object obj) throws Exception
	{return Boolean.valueOf(check.f(obj));}
}
