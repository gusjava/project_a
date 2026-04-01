package a.entity.gus06.sys.base1.holder.dir.g;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150524";}

	private File dir;
	
	public EntityImpl() throws Exception
	{dir = (File) Outside.resource(this,"defaultdir");}
	
	public Object g() throws Exception
	{return dir;}
}