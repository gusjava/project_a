package a.entity.gus06.string.transform.integer.tobinary;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			int n = Integer.parseInt((String) obj);
			return Integer.toBinaryString(n);
		}
		catch(NumberFormatException e) {}
		return obj;
	}
}