package a.entity.gus06.appli.gusclient1.execute.gus.testapp;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140811";}

	private Service cmd1;
	private Service cmd2;

	public EntityImpl() throws Exception
	{
		cmd1 = Outside.service(this,"gus06.command.rebuild.other");
		cmd2 = Outside.service(this,"gus06.app.jarfile.o.launch");
	}
	
	public void e() throws Exception
	{
		cmd1.e();
		cmd2.e();
	}
}