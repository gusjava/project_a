package a.entity.gus06.sys.expression1.apply.op._rem_start_i;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161229";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T2((String)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T2 implements T
	{
		private String value;
		public T2(String value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return value;
			
			if(obj instanceof Boolean) return rem(value,toString_(obj));
			if(obj instanceof Number) return rem(value,toString_(obj));
			if(obj instanceof String) return rem(value,(String) obj);
			if(obj instanceof String[]) return rem(value,(String[]) obj);
			if(obj instanceof List) return rem(value,(List) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private String rem(String s1, String s2) throws Exception
	{
		while(s1.toLowerCase().startsWith(s2.toLowerCase()))
		s1 = s1.substring(s2.length());
		return s1;
	}
	
	private String rem(String s1, String[] ss) throws Exception
	{
		for(String s2 : ss) s1 = rem(s1,s2);
		return s1;
	}
	
	private String rem(String s1, List ss) throws Exception
	{
		for(Object s : ss) s1 = rem(s1,toString_(s));
		return s1;
	}
	
	
	
	private String toString_(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return ""+obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof String) return ""+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
