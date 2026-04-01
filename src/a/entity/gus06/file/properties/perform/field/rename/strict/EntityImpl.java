package a.entity.gus06.file.properties.perform.field.rename.strict;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150924";}

	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String field = (String) o[1];
		String newName = (String) o[2];
		
		Properties prop = prop(file);
		if(!prop.containsKey(field)) throw new Exception("Field not found inside prop file: "+field);
		
		if(field.equals(newName)) return;
		
		String value = prop.getProperty(field);
		prop.remove(field);
		prop.put(newName,value);
		
		save(prop,file);
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
