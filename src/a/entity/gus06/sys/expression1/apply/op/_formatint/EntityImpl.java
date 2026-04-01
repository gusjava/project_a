package a.entity.gus06.sys.expression1.apply.op._formatint;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160407";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Long) return new T1(_long((Long) obj));
		if(obj instanceof Integer) return new T1(_long((Integer) obj));
		if(obj instanceof String) return new T1(_long((String) obj));
		
		return Boolean.valueOf(obj instanceof Integer);
	}
	
	
	
	private class T1 implements T
	{
		private long n;
		public T1(long n) {this.n = n;}
		
		public Object t(Object obj) throws Exception
		{
			String format = ""+obj;
			long l = _long(format);
			if(l==-1) l = format.length();
			
			String s = ""+n;
			while(s.length()<l) s = "0"+s;
			return s;
		}
	}
	
	private long _long(Number n)
	{
		return n.longValue();
	}
	
	private long _long(String s)
	{
		try{return Long.parseLong(s);}
		catch(NumberFormatException e)
		{return -1;}
	}
}