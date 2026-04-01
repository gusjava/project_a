package a.entity.gus06.filter.string.is.numberp;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180318";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		
		try
		{
			double d = Double.parseDouble(s);
			return d>=0;
		}
		catch(NumberFormatException e)
		{return false;}
	}
}