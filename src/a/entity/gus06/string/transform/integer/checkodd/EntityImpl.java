package a.entity.gus06.string.transform.integer.checkodd;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			int n = Integer.parseInt((String) obj);
			boolean check = n%2==1;
			return ""+check;
		}
		catch(NumberFormatException e) {}
		return obj;
	}
}