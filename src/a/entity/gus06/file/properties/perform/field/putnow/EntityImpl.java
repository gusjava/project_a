package a.entity.gus06.file.properties.perform.field.putnow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20170201";}


	private Service now;
	
	public EntityImpl() throws Exception
	{
		now = Outside.service(this,"gus06.time.now");
	}

	

	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String value = (String) o[1];
		
		Properties prop = prop(file);
		prop.put(now(),value);
		save(prop,file);
		
		return true;
	}

	
	
	private Properties prop(File file) throws Exception
	{
		Properties p = new Properties();
		if(file.exists())
		{
			FileInputStream fis = new FileInputStream(file);
			p.load(fis);
			fis.close();
		}
		return p;
	}
	
	private void save(Properties p, File file) throws Exception
	{
		file.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(file);
		p.store(fos,"");
		fos.close();
	}
	
	private String now() throws Exception
	{return (String) now.g();}
}