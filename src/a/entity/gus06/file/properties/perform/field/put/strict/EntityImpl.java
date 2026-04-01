package a.entity.gus06.file.properties.perform.field.put.strict;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20161205";}

	

	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String field = toString(o[1]);
		String value = toString(o[2]);
		
		Properties prop = prop(file);
		if(prop.containsKey(field)) throw new Exception("Key already used inside prop file: "+field);
		
		prop.put(field,value);
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
	
	private String toString(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}