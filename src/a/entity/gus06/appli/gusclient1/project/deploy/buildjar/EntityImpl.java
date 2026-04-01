package a.entity.gus06.appli.gusclient1.project.deploy.buildjar;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140902";}

	private Service jarBuilder;
	private Service getJarFile;
	private Service getEntryMap;
	private Service getMainClass;

	public EntityImpl() throws Exception
	{
		jarBuilder = Outside.service(this,"gus06.file.jar.builder1");
		getJarFile = Outside.service(this,"gus06.appli.gusclient1.project.deploy.getjar");
		getEntryMap = Outside.service(this,"gus06.appli.gusclient1.project.deploy.getentrymap");
		getMainClass = Outside.service(this,"gus06.app.jarfile.mainclass");
	}
	
	
	public void e() throws Exception
	{
		String mainClass = (String) getMainClass.g();
		File jarFile = (File) getJarFile.g();
		Map entry = (Map) getEntryMap.g();
		
		jarBuilder.v("jarFile",jarFile);
		jarBuilder.v("entryMap",entry);
		jarBuilder.v("mainClass",mainClass);
		jarBuilder.v("bin",null);
		
		jarBuilder.e();
	}
}
