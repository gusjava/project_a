package a.entity.gus06.string.transform.str.lower.c1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210722";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return lowerC1(s);
	}
	
	
	private String lowerC1(String s)
	{
		if(s.equals("")) return s;
		int n = s.length();
		return s.substring(0,n-1) + s.substring(n-1).toLowerCase();
	}
}