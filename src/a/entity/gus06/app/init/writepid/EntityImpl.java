package a.entity.gus06.app.init.writepid;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;


public class EntityImpl implements Entity {

	public String creationDate() {return "20140807";}


	private Service getPid;
	private File rootDir;
	

	public EntityImpl() throws Exception
	{
		getPid = Outside.service(this,"gus06.app.pid");
		rootDir = (File) Outside.resource(this,"rootdir");
		if(!rootDir.exists()) rootDir.mkdirs();
		
		String pid = (String) getPid.g();
		File file = new File(rootDir,"pid");
		
		PrintStream p = new PrintStream(file);
		p.print(pid);
		p.close();
	}
}
