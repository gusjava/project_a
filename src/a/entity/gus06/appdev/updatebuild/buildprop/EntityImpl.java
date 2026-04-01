package a.entity.gus06.appdev.updatebuild.buildprop;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140907";}

	
	public static final String KEY = "jar.buildtime";
	
	private Service now;
	

	public EntityImpl() throws Exception
	{now = Outside.service(this,"gus06.time.now");}
	
	
	public Object g() throws Exception
	{
		Properties p = new Properties();
		p.setProperty(KEY,(String) now.g());
		return p;
	}
}
