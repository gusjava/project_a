package a.entity.gus06.file.properties.perform.extend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}

	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Set fields = (Set) o[1];
		
		Properties prop = prop(file);
		boolean changed = false;
		
		Iterator it = fields.iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(!prop.containsKey(key))
			{
				prop.setProperty(key,"");
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
