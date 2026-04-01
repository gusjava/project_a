package a.entity.gus06.string.transform.str.same;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s;
	}
}
