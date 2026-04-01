package a.entity.gus06.file.properties.perform.field.get;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200315";}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String field = toString(o[1]);
		
		Properties prop = prop(file);
		if(!prop.containsKey(field)) return null;
		
		return prop.get(field);
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
	
	private String toString(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
