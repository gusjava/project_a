package a.entity.gus06.string.transform.line.length.min;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150929";}
	
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
		return ""+min;
	}
}
