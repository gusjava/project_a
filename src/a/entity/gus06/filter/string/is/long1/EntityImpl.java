package a.entity.gus06.filter.string.is.long1;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180318";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		
		try
		{
			Long.parseLong(s);
			return true;
		}
		catch(NumberFormatException e)
		{return false;}
	}
}