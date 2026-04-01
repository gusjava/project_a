package a.entity.gus06.sys.expression1.apply.op._rem_last;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161229";}
	
	private Service quote;
	
	public EntityImpl() throws Exception
	{quote = Outside.service(this,"gus06.string.transform.regexp.quote");}
	
	
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
			return rem(value,toString_(obj));
		}
	}
	
	
	
	private Object rem(String s1, String s2) throws Exception
	{
		Pattern p = Pattern.compile((String) quote.t(s2));
		Matcher m = p.matcher(s1);
		
		int start = -1;
		int end = -1;
		
		while(m.find())
		{
			start = m.start();
			end = m.end();
		}
		
		if(start==-1) return s1;
		return s1.substring(0,start)+s1.substring(end,s1.length());
	}
	
	
	
	private String toString_(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return ""+obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof String) return ""+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
