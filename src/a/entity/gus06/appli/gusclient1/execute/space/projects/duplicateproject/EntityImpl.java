package a.entity.gus06.appli.gusclient1.execute.space.projects.duplicateproject;

import a.framework.*;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140910";}


	private Service manager;
	private Service idToDir;
	private Service askDuplicate;
	private Service setProp;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir");
		askDuplicate = Outside.service(this,"gus06.dir.perform.duplicate.ask");
		setProp = Outside.service(this,"gus06.appli.gusclient1.project.config.setprop");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(invalid(id)) return;
		
		File dir = (File) idToDir.t(id);
		File newDir = (File) askDuplicate.t(dir);
		if(newDir==null) return;
		
		String newId = newDir.getName();
		manager.p(newId);
	}
	
	
	
	private boolean invalid(String s)
	{return s==null || s.equals("");}
}
