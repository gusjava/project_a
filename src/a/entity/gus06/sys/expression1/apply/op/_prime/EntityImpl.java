package a.entity.gus06.sys.expression1.apply.op._prime;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Integer) return Boolean.valueOf(isPrime(toInt(obj)));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	private boolean isPrime(int n)
	{
		if(n==0 || n==1) return false;
		
		for(int i=2;i<n;i++)
		{if(n%i==0)return false;}
		return true;
	}
}
