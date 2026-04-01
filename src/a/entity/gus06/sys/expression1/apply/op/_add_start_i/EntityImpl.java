package a.entity.gus06.sys.expression1.apply.op._add_start_i;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240409";}
	
	
	
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
			
			if(obj instanceof Boolean) return add(value,toString_(obj));
			if(obj instanceof Number) return add(value,toString_(obj));
			if(obj instanceof String) return add(value,(String) obj);
			if(obj instanceof String[]) return add(value,(String[]) obj);
			if(obj instanceof List) return add(value,(List) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private String add(String s1, String s2) throws Exception
	{
		if(s1.toLowerCase().startsWith(s2.toLowerCase())) return s1;
		return s2+s1;
	}
	
	private String add(String s1, String[] ss) throws Exception
	{
		for(String s2 : ss) s1 = add(s1,s2);
		return s1;
	}
	
	private String add(String s1, List ss) throws Exception
	{
		for(Object s : ss) s1 = add(s1,toString_(s));
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