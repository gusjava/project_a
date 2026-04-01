package a.entity.gus06.math.integer.primefactors;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231104";}


	private Service getPrimes;

	public EntityImpl() throws Exception
	{
		getPrimes = Outside.service(this,"gus06.math.prime.array.until300");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Integer n = (Integer) obj;
		if(n==null) return null;
		int val = n.intValue();
		
		int[] primeNumbers = (int[]) getPrimes.g();
		int limit = primeNumbers.length;
		
		int[] decomposition = new int[limit];
		for(int i=0;i<limit;i++)
		{
			int prime = primeNumbers[i];
			int p = 0;
			while(val>1 && val%prime==0)
			{p++; val = (int) (val/prime);}
			decomposition[i] = p;
		}
		
		if(val>1) return null;
		return decomposition;
	}
}