package a.entity.gus06.appli.gusclient1.execute.gus.rebuildapp;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140811";}

	private Service backup;
	private Service rebuild;

	public EntityImpl() throws Exception
	{
		backup = Outside.service(this,"gus06.command.backupapp");
		rebuild = Outside.service(this,"gus06.command.rebuild");
	}
	
	public void e() throws Exception
	{
		backup.e();
		rebuild.e();
	}
}
