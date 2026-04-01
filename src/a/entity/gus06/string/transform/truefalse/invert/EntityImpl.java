package a.entity.gus06.string.transform.truefalse.invert;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		s = s.toLowerCase();
		
		if(s.equals("true")) return "false";
		if(s.equals("false")) return "true";
		return obj;
	}
}
