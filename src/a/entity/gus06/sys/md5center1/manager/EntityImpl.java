package a.entity.gus06.sys.md5center1.manager;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, R, V, F {

	public String creationDate() {return "20161227";}


	private Service inside;
	private File storeDir;

	public EntityImpl() throws Exception
	{
		inside = Outside.service(this,"gus06.app.inside.md5center");
		storeDir = (File) Outside.resource(this,"defaultdir");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		return true;
	}
	
	
	public Object r(String key) throws Exception
	{
		return null;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		
	}
}
