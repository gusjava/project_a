package a.entity.gus06.string.transform.str.upper.c0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210722";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return upperC0(s);
	}
	
	private String upperC0(String s)
	{
		if(s.equals("")) return s;
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
}