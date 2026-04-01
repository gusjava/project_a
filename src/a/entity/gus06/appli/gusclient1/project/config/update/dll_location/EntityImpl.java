package a.entity.gus06.appli.gusclient1.project.config.update.dll_location;

import java.io.File;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150615";}

	
	private Service manager;
	private Service idToFile;
	private Service idToDir;
	private Service writeProp;
	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		idToFile = Outside.service(this,"gus06.appli.gusclient1.project.idtofile.resource.dll_location");
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.resource.dll");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(id==null) return;
		
		File file = (File) idToFile.t(id);
		File dir = (File) idToDir.t(id);
		
		File[] ff = dir.listFiles();
		Properties p = new Properties();
		if(ff!=null) for(File f:ff)
		{
			String name = f.getName();
			if(!name.endsWith(".dll")) throw new Exception("Invalid dll file name: "+f);
			
			String key = buildKey(name);
			String value = buildValue(name);
			
			p.put(key,value);
		}
		writeProp.p(new Object[]{file,p});
	}
	
	
	
	private String buildKey(String name)
	{
		if(name.startsWith("32-")) return name.substring(3);
		if(name.startsWith("64-")) return name.substring(3);
		return name;
	}
	
	private String buildValue(String name)
	{
		return "dll/"+name.replace("32","<arch>").replace("64","<arch>");
	}
}
