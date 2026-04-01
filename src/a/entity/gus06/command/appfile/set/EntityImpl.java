package a.entity.gus06.command.appfile.set;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import a.framework.*;

public class EntityImpl implements Entity, P, V, R {

	public String creationDate() {return "20140703";}

	private File appFile;

	public EntityImpl() throws Exception
	{
		appFile = (File) Outside.resource(this,"appfile");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("=",2);
		if(n.length!=2) throw new Exception("Invalid data: "+s);
		
		Properties prop = load();
		change(prop,n[0],n[1]);
		save(prop);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Properties prop = load();
		change(prop,key,(String) obj);
		save(prop);
	}
	
	
	public Object r(String key) throws Exception
	{
		Properties prop = load();
		return prop.containsKey(key)?prop.get(key):null;
	}
	
	
	
	private void change(Properties prop, String key, String value) throws Exception
	{
		if(value==null) prop.remove(key);
		else prop.put(key,value);
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
