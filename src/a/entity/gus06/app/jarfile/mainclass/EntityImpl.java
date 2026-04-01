package a.entity.gus06.app.jarfile.mainclass;

import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140716";}
	

	private Service appJar;
	private String mainClass;
	
	public EntityImpl() throws Exception
	{
		appJar = Outside.service(this,"gus06.app.jarfile");
	}
	
	
	public Object g() throws Exception
	{
		if(mainClass==null) init();
		return mainClass;
	}
	
	
	
	
	private void init() throws Exception
	{
		File file = (File) appJar.g();
		JarFile jar = new JarFile(file,true,JarFile.OPEN_READ);

		try
		{
			Manifest mf = jar.getManifest();
			Attributes attrs = mf.getMainAttributes();
			if(!attrs.containsKey(Attributes.Name.MAIN_CLASS))
				throw new Exception("Manifest entry key not found: "+Attributes.Name.MAIN_CLASS);
			mainClass = (String) attrs.get(Attributes.Name.MAIN_CLASS);
		}
		finally {jar.close();}
	}
}
