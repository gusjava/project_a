package a.entity.gus06.appli.gusclient1.project.config.load1.info;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150601";}


	private Service manager;
	private Service idToFile;
	private Service readProp;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtofile.info");
		readProp = Outside.service(this,"gus06.file.read.properties");
	}
	
	
	
	public Object g() throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return null;
		
		File file = (File) idToFile.t(id);
		if(!file.exists()) throw new Exception("Info file not found: "+file);
		
		return readProp.t(file);
	}
}
