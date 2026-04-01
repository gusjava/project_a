package a.entity.gus06.system.prop.userdir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180424";}
	
	public static final String KEY = "user.dir";
	
	
	public Object g() throws Exception
	{return new File(System.getProperty(KEY));}
}
