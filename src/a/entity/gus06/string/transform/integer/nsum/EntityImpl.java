package a.entity.gus06.string.transform.integer.nsum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			int n = Integer.parseInt((String) obj);
			return ""+nsum(n);
		}
		catch(NumberFormatException e) {}
		return obj;
	}
	
	private int nsum(int n)
	{
		int sum = 0;
		for(int i=1;i<=n;i++) sum += i;
		return sum;
	}
}