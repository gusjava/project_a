package a.entity.gus06.string.transform.line.extend;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160510";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		int max = 0;
		for(String n:nn)
		{
			int length = n.trim().length();
			if(length>max) max = length; 
		}
		
		StringBuffer b = new StringBuffer();
		for(String n:nn)
		b.append(complete(n,max)+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private String complete(String s, int n)
	{
		while(s.length()<n) s = s+" ";
		return s;
	}
}
