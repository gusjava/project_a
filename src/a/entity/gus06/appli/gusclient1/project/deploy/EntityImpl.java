package a.entity.gus06.appli.gusclient1.project.deploy;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl extends S1 implements Entity, E {

	public String creationDate() {return "20140905";}


	private Service updateBuild;
	private Service buildJar;
	private Service initDir;
	
	private PrintStream out;


	public EntityImpl() throws Exception
	{
		updateBuild = Outside.service(this,"gus06.appli.gusclient1.project.config.updatebuild");
		buildJar = Outside.service(this,"gus06.appli.gusclient1.project.deploy.buildjar");
		initDir = Outside.service(this,"gus06.appli.gusclient1.project.deploy.initdir");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void e() throws Exception
	{
		out.println("> deploying application");
		
		updateBuild.e();
		buildJar.e();
		initDir.e();
		
		complete();
		
		out.println("application deployed");
	}
	
	
	private void complete()
	{send(this,"complete()");}
}