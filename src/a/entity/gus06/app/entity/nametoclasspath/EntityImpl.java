package a.entity.gus06.app.entity.nametoclasspath;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140916";}

	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		return "a.entity."+name+".EntityImpl";
	}
}