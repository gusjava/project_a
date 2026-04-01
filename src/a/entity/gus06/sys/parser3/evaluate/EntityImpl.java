package a.entity.gus06.sys.parser3.evaluate;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151031";}


	private Service prepare;
	private Service resolver1;
	private Service resolver2;
	
	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.sys.parser3.prepare");
		resolver1 = Outside.service(this,"gus06.sys.parser3.resolver1");
		resolver2 = Outside.service(this,"gus06.sys.parser3.resolver2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return resolve1(obj);
		if(obj instanceof List) return resolve1(obj);
			
		Object[] o = (Object[]) obj;
		if(o.length==2) return resolve2((T) o[0],o[1]);
		if(o.length==3) return resolve3((T) o[0],o[1],o[2]);
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	
	
	private Object resolve1(Object obj) throws Exception
	{
		try
		{
			T t = (T) resolver1.t(null);
			return t.t(toList(obj));
		}
		catch(Exception e)
		{
			String message = "Failed to resolve: "+obj;
			throw new Exception(message,e);
		}
	}
	
	
	private Object resolve2(T external, Object obj) throws Exception
	{
		try
		{
			T t = (T) resolver1.t(external);
			return t.t(toList(obj));
		}
		catch(Exception e)
		{
			String message = "Failed to resolve: "+obj;
			throw new Exception(message,e);
		}
	}
	
	
	private Object resolve3(T external, Object obj, Object rule) throws Exception
	{
		try
		{
			return resolver2.t(new Object[]{external,toList(obj),rule});
		}
		catch(Exception e)
		{
			String message = "Failed to resolve: "+obj+" with rule: "+rule;
			throw new Exception(message,e);
		}
	}
	
	
	
	private List toList(Object obj) throws Exception
	{
		if(obj instanceof List) return (List) obj;
		return (List) prepare.t(obj);
	}
}
