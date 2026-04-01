package a.entity.gus06.sys.expression1.apply.op._factorial;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Integer) return Integer.valueOf(factorial(toInt(obj)));
		if(obj instanceof Long) return Long.valueOf(factorial(toLong(obj)));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}
	
	
	private int factorial(int n)
	{
		int fac = 1;
		for(int i=1;i<=n;i++) fac *= i;
		return fac;
	}
	
	private long factorial(long n)
	{
		long fac = 1;
		for(int i=1;i<=n;i++) fac *= i;
		return fac;
	}
}