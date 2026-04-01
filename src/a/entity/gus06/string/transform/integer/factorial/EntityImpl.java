package a.entity.gus06.string.transform.integer.factorial;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			int n = Integer.parseInt((String) obj);
			return ""+factorial(n);
		}
		catch(NumberFormatException e) {}
		return obj;
	}
	
	private int factorial(int n)
	{
		int fac = 1;
		for(int i=1;i<=n;i++) fac *= i;
		return fac;
	}
}