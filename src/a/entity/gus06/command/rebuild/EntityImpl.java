package a.entity.gus06.command.rebuild;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140716";}

	private Service updateBuild;
	private Service rebuildJar;

	public EntityImpl() throws Exception
	{
		updateBuild = Outside.service(this,"gus06.appdev.updatebuild");
		rebuildJar = Outside.service(this,"gus06.app.jarfile.rebuild1");
	}
	
	
	public void e() throws Exception
	{
		updateBuild.e();
		rebuildJar.e();
		
		System.exit(0);
	}
}
