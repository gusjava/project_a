package a.entity.gus06.appli.gusclient1.execute.space.projects.releaseproject;

import a.framework.*;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20141014";}


	private Service release;


	public EntityImpl() throws Exception
	{
		release = Outside.service(this,"gus06.appli.gusclient1.project.release");
	}
	
	public void e() throws Exception
	{
		release.e();
	}
}
