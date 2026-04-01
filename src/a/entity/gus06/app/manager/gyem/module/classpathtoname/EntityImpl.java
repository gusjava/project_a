package a.entity.gus06.app.manager.gyem.module.classpathtoname;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140917";}

	public static final String MODULE_START = "gus06.manager.gus.gyem.";
	public static final String MODULE_END = ".Module";
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String classpath = (String) obj;
		return classpath.substring(MODULE_START.length(),classpath.length()-MODULE_END.length());
	}
}
