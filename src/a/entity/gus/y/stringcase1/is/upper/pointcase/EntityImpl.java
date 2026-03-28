package a.entity.gus.y.stringcase1.is.upper.pointcase;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20240714";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		return check((String) obj);
	}
	
	private boolean check(String s)
	{
		return s.matches("[A-Z][A-Z0-9]*(\\.[A-Z0-9]*)*");
	}
}