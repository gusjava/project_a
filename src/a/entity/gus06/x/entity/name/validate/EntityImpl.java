package a.entity.gus06.x.entity.name.validate;

import a.framework.*;

public class EntityImpl implements Entity, F, G {

	public String creationDate() {return "20251113";}
	
	public static final String REGEX = "[a-z][a-z0-9]{2,9}(\\.[a-z_][a-z0-9_]*)+";

	public boolean f(Object obj) throws Exception
	{
		String name = (String) obj;
		if (name == null) return false;
		return name.matches(REGEX);
	}

	public Object g() throws Exception
	{return REGEX;}
}
