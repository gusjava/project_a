package a.entity.gus06.command.appfile.replace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140707";}


	private Service handle;
	private File appFile;

	public EntityImpl() throws Exception
	{
		handle = Outside.service(this,"gus06.command.appfile.replace.handle");
		appFile = (File) Outside.resource(this,"appfile");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		
		Properties prop = load();
		handle.v(s,prop);
		save(prop);
	}
	
	
	private Properties load() throws Exception
	{
		Properties prop = new Properties();
		if(!appFile.exists()) return prop;
		
		FileInputStream fis = new FileInputStream(appFile);
		prop.load(fis);
		fis.close();
		return prop;
	}
	
	
	private void save(Properties prop) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(appFile);
		prop.store(fos,"");
		fos.close();
	}
}
