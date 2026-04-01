package a.entity.gus06.string.case1.is.upper.spacecase;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220505";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		return check((String) obj);
	}
	
	private boolean check(String s)
	{
		return s.matches("[A-Z][A-Z0-9]*( [A-Z0-9]*)*");
	}
}