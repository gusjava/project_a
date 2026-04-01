package a.entity.gus06.string.transform.integer.double1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			int n = Integer.parseInt((String) obj);
			return ""+(n*2);
		}
		catch(NumberFormatException e) {}
		return obj;
	}
}