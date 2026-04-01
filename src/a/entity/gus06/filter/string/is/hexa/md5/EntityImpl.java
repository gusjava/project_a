package a.entity.gus06.filter.string.is.hexa.md5;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220208";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.matches("[0-9a-fA-F]{32}");
	}
}