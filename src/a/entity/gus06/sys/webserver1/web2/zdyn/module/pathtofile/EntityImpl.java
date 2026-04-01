package a.entity.gus06.sys.webserver1.web2.zdyn.module.pathtofile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, R, T {

	public String creationDate() {return "20141129";}

	public static final String S = File.separator;

	private File rootDir;

	public EntityImpl() throws Exception
	{
		rootDir = (File) Outside.resource(this,"path#path.web2.dir.spaces");
		rootDir.mkdirs();
	}
	
	public Object r(String key) throws Exception
	{
		key = key.replace("\\",S).replace("/",S);
		return new File(rootDir,key);
	}
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
}
