package a.entity.gus06.command.compareapp;

import java.io.PrintStream;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140707";}

	
	private Service socket;
	private Service jarMd5;
	private PrintStream out;
	

	public EntityImpl() throws Exception
	{
		socket = Outside.service(this,"gus06.command.socket");
		jarMd5 = Outside.service(this,"gus06.app.jarfile.md5.c");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void e() throws Exception
	{
		String md5_remote = (String) socket.t("jarmd5");
		if(md5_remote.startsWith("string:")) md5_remote = md5_remote.substring(7);
		
		String md5_local = (String) jarMd5.g();
		
		if(md5_remote.equals(md5_local)) out.println("app jars: identical");
		else out.println("app jars: different");
	}
}
