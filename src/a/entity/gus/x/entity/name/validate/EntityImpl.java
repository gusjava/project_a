package a.entity.gus.x.entity.name.validate;

import a.framework.*;

public class EntityImpl implements Entity, F, G {
	public String creationDate() {return "20231202";}
	
	public static final String REGEX = "[a-z][a-z0-9]{2,9}(\\.[a-z][a-z0-9_]*)+";

	public boolean f(Object obj) throws Exception {
		String name = (String) obj;
		if (name == null)
			return false;
		return name.matches(REGEX);
	}

	public Object g() throws Exception {
		return REGEX;
	}
}
