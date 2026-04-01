package a.entity.gus06.appli.gusclient1.project.config.setprop.addmodel;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141028";}
	
	public static final String KEY = "model";


	private Service manager;
	private Service idToFile;
	private Service modify;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtofile.resource.prop");
		modify = Outside.service(this,"gus06.file.modify.properties.keyvalue.seq1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return;
		
		File propFile = (File) idToFile.t(id);
		modify.p(new Object[]{propFile,KEY,obj});
	}
}
