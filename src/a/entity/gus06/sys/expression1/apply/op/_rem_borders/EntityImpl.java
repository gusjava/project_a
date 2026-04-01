package a.entity.gus06.sys.expression1.apply.op._rem_borders;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}
	
	
	
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
			return rem(value,(String) obj);
		}
	}
	
	
	private Object rem(String s1, String s2) throws Exception
	{
		while(s1.startsWith(s2))
			s1 = s1.substring(s2.length());
		while(s1.endsWith(s2))
			s1 = s1.substring(0,s1.length()-s2.length());
		return s1;
	}
}
