package a.entity.gus06.command.appfile.unset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140703";}


	private File appFile;

	public EntityImpl() throws Exception
	{
		appFile = (File) Outside.resource(this,"appfile");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(!appFile.exists()) return;

		Properties prop = load();
		prop.remove(s);
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
