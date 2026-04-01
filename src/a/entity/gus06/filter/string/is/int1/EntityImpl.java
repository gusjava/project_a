package a.entity.gus06.filter.string.is.int1;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160403";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		
		try
		{
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException e)
		{return false;}
	}
}