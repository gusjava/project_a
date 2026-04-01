package a.entity.gus06.string.transform.str.lower.c0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210722";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return lowerC0(s);
	}
	
	
	private String lowerC0(String s)
	{
		if(s.equals("")) return s;
		return s.substring(0,1).toLowerCase() + s.substring(1);
	}
}