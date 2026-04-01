package a.entity.gus06.filter.string.is.intn;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180318";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		
		try
		{
			int n = Integer.parseInt(s);
			return n<0;
		}
		catch(NumberFormatException e)
		{return false;}
	}
}