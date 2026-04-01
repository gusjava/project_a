package a.entity.gus06.string.transform.integer.next;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			int n = Integer.parseInt((String) obj);
			return ""+(n+1);
		}
		catch(NumberFormatException e) {}
		return obj;
	}
}