package a.entity.gus06.ling.gui.lingdir.register;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Map;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140829";}



	private Service langManager;
	private Service findResource;
	
	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		langManager = Outside.service(this,"gus06.ling.language.manager");
		findResource = Outside.service(this,"gus06.ling.find.resource");
		
		dir = (File) Outside.resource(this,"path#path.dev.lingdir");
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(dir==null || !dir.exists()) return;
		registerValue(key,(String) obj);
	}
	
	
	
	
	private void registerValue(String key, String value) throws Exception
	{
		String[] info = analyze(key);
		String name = info[0];
		String lingKey = info[1];
		
		Map map = (Map) findResource.r(name);
		if(map!=null) map.put(lingKey,value);
		
		String lang = (String) langManager.g();
		File file = new File(dir,name+"_"+lang);
		Properties prop = load(file);
		prop.put(lingKey,value);
		save(prop,file);
	}
	
	
	
	
	private String[] analyze(String key)
	{
		if(!key.contains("_")) return new String[]{"default",key};
		return key.split("_",2);
	}
	
	
	
	private Properties load(File file) throws Exception
	{
		Properties prop = new Properties();
		if(!file.exists()) return prop;
		
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		fis.close();
		return prop;
	}
	
	
	private void save(Properties prop, File file) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(file);
		prop.store(fos,"");
		fos.close();
	}
}
