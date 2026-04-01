package a.entity.gus06.appli.gusclient1.project.deploy.initdir;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140905";}

	public static final String LAUNCH_CMD = "java -jar app.jar\npause";

	private Service idToDir;
	private Service manager;


	public EntityImpl() throws Exception
	{
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.deploy");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return;
		
		File dir = (File) idToDir.t(id);
		File batFile = new File(dir,"launch.bat");
		PrintStream p = new PrintStream(batFile);
		p.print(LAUNCH_CMD);
		p.close();
	}
}
