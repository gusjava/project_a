package a.entity.gus06.file.properties.perform.field.empty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150926";}

	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String field = (String) o[1];
		
		Properties prop = prop(file);
		if(!prop.containsKey(field)) return;
		
		String value0 = prop.getProperty(field);
		if(value0.equals("")) return;
		
		prop.put(field,"");
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
