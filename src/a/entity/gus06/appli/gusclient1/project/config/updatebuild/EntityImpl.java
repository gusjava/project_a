package a.entity.gus06.appli.gusclient1.project.config.updatebuild;

import java.io.File;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, E, P {

	public String creationDate() {return "20140907";}

	public static final String KEY_VERSION = "app.version";	
	
	private Service manager;
	private Service idToFile;
	private Service buildProp;
	private Service writeProp;
	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtofile.resource.p_build");
		buildProp = Outside.service(this,"gus06.appdev.updatebuild.buildprop");
		writeProp = Outside.service(this,"gus06.file.write.properties.merge.replace");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return;
		
		File file = (File) idToFile.t(id);
		Properties p = (Properties) buildProp.g();
		writeProp.p(new Object[]{file,p});
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		String version = (String) obj;
		
		String id = (String) manager.g();
		if(id==null) return;
		
		File file = (File) idToFile.t(id);
		Properties p = (Properties) buildProp.g();
		if(version!=null) p.put(KEY_VERSION,version);
		writeProp.p(new Object[]{file,p});
	}
}
