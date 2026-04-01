package a.entity.gus06.string.transform.integer.tooctal;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			int n = Integer.parseInt((String) obj);
			return Integer.toOctalString(n);
		}
		catch(NumberFormatException e) {}
		return obj;
	}
}