package a.entity.gus06.math.integer.primefactors.display;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231104";}


	private Service getPrimes;
	private Service build;

	public EntityImpl() throws Exception
	{
		getPrimes = Outside.service(this,"gus06.math.prime.array.until300");
		build = Outside.service(this,"gus06.math.integer.primefactors");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		int[] decomposition = toDecomposition(obj);
		if(decomposition==null) return null;
		
		int[] primeNumbers = (int[]) getPrimes.g();
		int limit = Math.min(decomposition.length, primeNumbers.length);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<limit;i++)
		{
			int p = decomposition[i];
			if(p>0)
			{
				int prime = primeNumbers[i];
				if(b.length()>0) b.append(" * ");
				b.append(prime);
				if(p>1) b.append("^"+p);
			}
		}
		return b.toString();
	}
	
	
	private int[] toDecomposition(Object obj) throws Exception
	{
		if(obj instanceof int[]) return (int[]) obj;
		if(obj instanceof Integer) return (int[]) build.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}