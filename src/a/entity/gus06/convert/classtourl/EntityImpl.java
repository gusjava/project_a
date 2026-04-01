package a.entity.gus06.convert.classtourl;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140705";}

	
	public Object t(Object obj) throws Exception
	{
		Class c = (Class) obj;
		ClassLoader loader = c.getClassLoader();
		String filename = c.getName().replace('.', '/') + ".class";
		return loader!=null?loader.getResource(filename):ClassLoader.getSystemResource(filename);
	}
}
