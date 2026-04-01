package a.entity.gus06.sys.expression1.apply.op._is_december;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160801";}
	
	
	private Service check;
	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.time.date.is.m12.december");
		findDate = Outside.service(this,"gus06.find.date");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		
		if(obj instanceof Date) return check(obj);
		if(obj instanceof Long) return check(findDate.t(obj));
		if(obj instanceof int[]) return check(findDate.t(obj));
		if(obj instanceof String) return check(findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Boolean check(Object obj) throws Exception
	{return Boolean.valueOf(check.f(obj));}
}
