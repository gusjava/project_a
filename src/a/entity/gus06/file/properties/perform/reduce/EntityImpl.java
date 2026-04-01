package a.entity.gus06.file.properties.perform.reduce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}

	

	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		
		Properties prop = prop(file);
		boolean changed = false;
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = prop.getProperty(key);
			
			if(value.equals(""))
			{
				it.remove();
				changed = true;
			}
		}
		if(changed) save(prop,file);
	}

	
	
	private Properties prop(File file) throws Exception
	{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		return p;
	}
	
	private void save(Properties p, File file) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(file);
		p.store(fos,"");
		fos.close();
	}
}
