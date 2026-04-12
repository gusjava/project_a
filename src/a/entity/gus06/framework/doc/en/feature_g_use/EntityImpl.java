package a.entity.gus06.framework.doc.en.feature_g_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The G feature is typically used for generating or providing objects.
An entity implementing G can either construct a new instance when invoked, or return a pre-stored object.

Entity Example :

package gus06.entity.gus.system.prop.userhome;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180424";}
	
	public static final String KEY = "user.home";
	
	public Object g() throws Exception
	{return new File(System.getProperty(KEY));}
}
""";
	}
}