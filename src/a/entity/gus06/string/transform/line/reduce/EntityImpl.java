package a.entity.gus06.string.transform.line.reduce;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160509";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		int min = Integer.MAX_VALUE;
		for(String n:nn)
		{
			int length = n.trim().length();
			if(length<min) min = length; 
		}
		
		StringBuffer b = new StringBuffer();
		for(String n:nn)
		b.append(n.substring(0,min)+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
