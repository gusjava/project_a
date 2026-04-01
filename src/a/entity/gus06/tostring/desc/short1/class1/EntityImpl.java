package a.entity.gus06.tostring.desc.short1.class1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180130";}

	
	public EntityImpl() throws Exception
	{}


	public Object t(Object obj) throws Exception
	{
		Class c = (Class) obj;
		return "Class: "+c.getName();
	}
}
