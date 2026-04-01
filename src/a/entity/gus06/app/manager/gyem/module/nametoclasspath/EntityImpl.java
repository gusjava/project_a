package a.entity.gus06.app.manager.gyem.module.nametoclasspath;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140916";}

	public static final String MODULE_START = "gus06.manager.gus.gyem.";
	public static final String MODULE_END = ".Module";
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String moduleName = (String) obj;
		return MODULE_START+moduleName+MODULE_END;
	}
}
