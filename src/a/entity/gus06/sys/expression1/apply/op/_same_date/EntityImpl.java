package a.entity.gus06.sys.expression1.apply.op._same_date;

import a.framework.*;
import java.util.Date;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231115";}
	
	private Service check;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.time.date.same.date");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Object[]) return check.f(obj);
		if(obj instanceof List) return check.f(obj);
		
		if(obj instanceof Date) return new T1(obj);
		if(obj instanceof Long) return new T1(obj);
		if(obj instanceof int[]) return new T1(obj);
		if(obj instanceof String) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Boolean check(Object obj) throws Exception
	{return Boolean.valueOf(check.f(obj));}
	
	
	private class T1 implements T
	{
		private Object elem1;
		public T1(Object elem1) {this.elem1 = elem1;}
		
		public Object t(Object obj) throws Exception
		{return check(new Object[]{elem1,obj});}
	}
}
