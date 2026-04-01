package a.entity.gus06.string.transform.str.lower.c01;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210722";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return lowerC01(s);
	}
	
	
	private String lowerC01(String s)
	{
		if(s.length()<3) return l(s);
		int n = s.length();
		return l(s.substring(0,1)) + s.substring(1,n-1) + l(s.substring(n-1));
	}
	
	private String l(String s)
	{return s.toLowerCase();}
}