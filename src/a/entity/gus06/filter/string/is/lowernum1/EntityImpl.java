package a.entity.gus06.filter.string.is.lowernum1;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160729";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.matches("[a-z0-9_]+");
	}
}