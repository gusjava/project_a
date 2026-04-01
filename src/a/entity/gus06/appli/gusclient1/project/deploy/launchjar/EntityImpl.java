package a.entity.gus06.appli.gusclient1.project.deploy.launchjar;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;


public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140904";}


	private Service getJarFile;
	private Service launchJar;
	
	private PrintStream out;


	public EntityImpl() throws Exception
	{
		getJarFile = Outside.service(this,"gus06.appli.gusclient1.project.deploy.getjar");
		launchJar = Outside.service(this,"gus06.java.launchjar");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void e() throws Exception
	{
		File jarFile = (File) getJarFile.g();
		out.println("> launching jar: "+jarFile);
		
		if(jarFile==null || !jarFile.exists())
		{
			out.println("jar not found");
			return;
		}
		
		launchJar.p(jarFile);
		out.println("jar launched");
	}
}