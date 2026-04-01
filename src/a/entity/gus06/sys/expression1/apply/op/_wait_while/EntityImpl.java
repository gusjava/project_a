package a.entity.gus06.sys.expression1.apply.op._wait_while;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170819";}


	private Service builder;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.f");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof F) return new E1((F) value);
		if(value instanceof String) return new E1(toF(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private F toF(Object obj) throws Exception
	{return (F) builder.t(obj);}
	
	
	
	private class E1 implements E
	{
		private F filter;
		
		public E1(F filter)
		{this.filter = filter;}
		
		public void e() throws Exception
		{while(filter.f(null)) {sleep_5();}}
	}
	
	private void sleep_5()
	{
		try{Thread.sleep(5);}
		catch(Exception e)
		{Outside.err(this,"sleep_5()",e);}
	}
}
