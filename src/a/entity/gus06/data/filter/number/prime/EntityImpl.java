package a.entity.gus06.data.filter.number.prime;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160819";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		if(!(obj instanceof Number)) return false;
		
		int v = ((Number) obj).intValue();
		return isPrime(v);
	}
	
	private boolean isPrime(int n)
	{
		if(n==0 || n==1) return false;
		
		for(int i=2;i<n;i++)
		{if(n%i==0)return false;}
		return true;
	}
}
