package a.entity.gus06.filter.string.is.trimable;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191209";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return !s.trim().equals(s);
	}
}