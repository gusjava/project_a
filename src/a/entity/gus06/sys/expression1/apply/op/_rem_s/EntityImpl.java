package a.entity.gus06.sys.expression1.apply.op._rem_s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160428";}
	
	
	private Service quote;
	
	public EntityImpl() throws Exception
	{
		quote = Outside.service(this,"gus06.string.transform.regexp.quote");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T2((String) obj);
		
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
		s2 = (String) quote.t(s2);
		s2 = s2.replaceAll("\\s+","\\\\s+");
		
		return s1.replaceAll(s2,"");
	}
}
