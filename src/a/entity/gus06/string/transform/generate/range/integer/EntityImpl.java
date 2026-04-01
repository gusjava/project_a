package a.entity.gus06.string.transform.generate.range.integer;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170124";}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(":",2);
		
		String type = n[0].trim();
		String delim = n.length==2?n[1]:"";
		
		boolean inv = false;
		if(type.endsWith(" inv"))
		{
			inv = true;
			type = type.substring(0,type.length()-3).trim();
		}
		
		String[] k = type.split(" ");
		return buildRange(k,delim,inv);
	}
	
	
	
	private String buildRange(String[] k, String delim, boolean inv)
	{
		int start0 = k.length>1 ? int_(k[0]) : 0;
		int end0 = k.length>1 ? int_(k[1]) : int_(k[0]);
		String type = k.length>2 ? k[2] : "";
		
		int start = inv ? end0 : start0;
		int end = inv ? start0 : end0;
		
		
		StringBuffer b = new StringBuffer();
		if(start<=end)
		{
			for(int i=start;i<=end;i++)
			if(isValid(i,type,start,end))
			{
				b.append(i);
				if(i<end) b.append(delim);
			}
		}
		else
		{
			for(int i=start;i>=end;i--)
			if(isValid(i,type,start,end))
			{
				b.append(i);
				if(i>end) b.append(delim);
			}
		}
		return b.toString();
	}
	
	
	private boolean isValid(int i, String type, int start, int end)
	{
		if(type.startsWith("!")) return !isValid(i,type.substring(1),start,end);
		
		if(type.equals("even")) return i%2==0;
		if(type.equals("odd")) return i%2==1;
		if(type.equals("prime")) return isPrime(i);
		
		if(isInt(type)) return (i-start)%int_(type)==0;
		
		return true;
	}
	
	private boolean isPrime(int n)
	{
		if(n==0 || n==1) return false;
		
		for(int i=2;i<n;i++)
		{if(n%i==0)return false;}
		return true;
	}
	
	private boolean isInt(String s)
	{
		try{Integer.parseInt(s);return true;}
		catch(NumberFormatException e){return false;}
	}
	
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}
