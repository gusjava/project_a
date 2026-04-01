package a.entity.gus06.app.prop.set1;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20251209";}


	private Map prop;
	private File appFile;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
		appFile = (File) Outside.resource(this,"appfile");
	}
	
	public void v(String key, Object obj) throws Exception
	{
		String value = obj!=null ? ""+obj : null;
		Properties prop1 = load();
		change(prop1, key, value);
		change(prop, key, value);
		save(prop1);
	}
	
	public void p(Object obj) throws Exception
	{
		Map m = (Map) obj;
		Properties prop1 = load();
		prop1.putAll(m);
		prop.putAll(m);
		save(prop1);
	}
	
	private void change(Map map, String key, String value) throws Exception
	{
		if(value==null) map.remove(key);
		else map.put(key,value);
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
