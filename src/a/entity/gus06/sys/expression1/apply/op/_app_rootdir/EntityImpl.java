package a.entity.gus06.sys.expression1.apply.op._app_rootdir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160916";}

	public static final String T = "constant";
	

	private File rootDir;
		
	public EntityImpl() throws Exception
	{
		rootDir = (File) Outside.resource(this,"rootdir");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return rootDir;
	}
}
