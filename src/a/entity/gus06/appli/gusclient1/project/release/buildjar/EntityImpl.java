package a.entity.gus06.appli.gusclient1.project.release.buildjar;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141015";}
	
	public static final String FILENAME_JAR = "app.jar";


	private Service jarBuilder;
	private Service getEntryMap;
	private Service getMainClass;

	public EntityImpl() throws Exception
	{
		jarBuilder = Outside.service(this,"gus06.file.jar.builder1");
		getEntryMap = Outside.service(this,"gus06.appli.gusclient1.project.deploy.getentrymap");
		getMainClass = Outside.service(this,"gus06.app.jarfile.mainclass");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		Map entry = (Map) getEntryMap.g();
		String mainClass = (String) getMainClass.g();
		File jarFile = new File(dir,FILENAME_JAR);
		
		jarBuilder.v("jarFile",jarFile);
		jarBuilder.v("entryMap",entry);
		jarBuilder.v("mainClass",mainClass);
		jarBuilder.v("bin",null);
		
		jarBuilder.e();
	}
}
