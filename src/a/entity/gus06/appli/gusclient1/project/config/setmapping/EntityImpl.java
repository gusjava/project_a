package a.entity.gus06.appli.gusclient1.project.config.setmapping;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20141028";}


	private Service manager;
	private Service idToFile;
	private Service modify;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtofile.resource.mapping");
		modify = Outside.service(this,"gus06.file.modify.properties.keyvalue");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return;
		
		File mappingFile = (File) idToFile.t(id);
		modify.p(new Object[]{mappingFile,key,obj});
	}
}
