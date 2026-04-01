package a.entity.gus06.string.transform.sequence.length.min;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150929";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split(DELIM,-1);
		
		int min = Integer.MAX_VALUE;
		for(String n:nn)
		{
			int length = n.length();
			if(length<min) min = length; 
		}
		return ""+min;
	}
}
