package a.entity.gus06.file.properties.perform.rm.empty;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150702";}

	

	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Properties prop = prop(file);
		if(prop.isEmpty()) delete(file);
	}

	
	private Properties prop(File file) throws Exception
	{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		return p;
	}
	
	
	private void delete(File file) throws Exception
	{
		boolean r = file.delete();
		if(!r) throw new Exception("Failed to delete file: "+file);
	}
}
