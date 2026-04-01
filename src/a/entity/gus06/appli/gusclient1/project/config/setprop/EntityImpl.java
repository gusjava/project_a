package a.entity.gus06.appli.gusclient1.project.config.setprop;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140905";}


	private Service manager;
	private Service idToFile;
	private Service modify;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtofile.resource.prop");
		modify = Outside.service(this,"gus06.file.modify.properties.keyvalue");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return;
		
		File propFile = (File) idToFile.t(id);
		modify.p(new Object[]{propFile,key,obj});
	}
}
