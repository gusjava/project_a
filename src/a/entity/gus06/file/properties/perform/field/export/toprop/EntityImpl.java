package a.entity.gus06.file.properties.perform.field.export.toprop;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151018";}

	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String field = (String) o[1];
		Map prop1 = (Map) o[2];
		
		Properties prop = prop(file);
		if(!prop.containsKey(field)) return;
		
		String key = getKey(file);
		String value = prop.getProperty(field);
		
		prop1.put(key,value);
	}

	
	
	private Properties prop(File file) throws Exception
	{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		return p;
	}
	
	
	private String getKey(File file)
	{
		String name = file.getName();
		if(!name.contains(".")) return name;
		String[] n = name.split("\\.");
		String ext = n[n.length-1];
		return name.substring(0,name.length()-ext.length()-1);
	}
}
