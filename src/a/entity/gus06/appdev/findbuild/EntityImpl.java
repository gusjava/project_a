package a.entity.gus06.appdev.findbuild;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140907";}


	public static final String KEY = "jar.buildtime";
	
	private Service readProp;


	private File file;

	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus.x.file.prop.read");
		file = (File) Outside.resource(this,"dev_p_build");
	}
	
	
	public Object g() throws Exception
	{
		Properties p = (Properties) readProp.t(file);
		if(p==null || p.isEmpty()) return null;
		return p.get(KEY);
	}
}
