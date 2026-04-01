package a.entity.gus06.sys.expression1.apply.op._rem_ncc;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161229";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private String value;
		public T1(String value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return value;
			return rem(value,(String) obj);
		}
	}
	
	
	private Object rem(String s1, String s2) throws Exception
	{
		StringBuffer b = new StringBuffer();
		int len = s1.length();
		for(int i=0;i<len;i++)
		{
			char c = s1.charAt(i);
			if(s2.indexOf(c)>=0) b.append(c);
		}
		return b.toString();
	}
	
}
