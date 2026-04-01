package a.entity.gus06.string.transform.truefalse.clearfalse;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.equals("false")?"":s;
	}
}
