package a.entity.gus06.appli.gusclient1.project.deploy.getjar;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140902";}

	private Service idToFile;
	private Service manager;


	public EntityImpl() throws Exception
	{
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtofile.deploy.jar");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
	}
	
	public Object g() throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return null;
		return idToFile.t(id);
	}
}
