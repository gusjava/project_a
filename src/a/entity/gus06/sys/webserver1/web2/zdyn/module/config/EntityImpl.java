package a.entity.gus06.sys.webserver1.web2.zdyn.module.config;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Properties;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140930";}


	private Service loadProp;


	public EntityImpl() throws Exception
	{loadProp = Outside.service(this,"gus06.file.read.properties");}
	
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((Map) obj);}
	
	
	
	
	private Properties build(Map dirs, String key) throws Exception
	{
		File dir0 = (File) dirs.get("config0");
		File dir1 = (File) dirs.get("config1");
		
		File file0 = new File(dir0,key+".ini");
		File file1 = new File(dir1,key+".ini");
		
		if(!file1.exists())
		{
			dir1.mkdirs();
			file1.createNewFile();
		}
		
		if(!file0.exists()) return prop(file1);
		
		Properties p = prop(file0);
		p.putAll(prop(file1));
		return p;
	}
	
	
	
	private Properties prop(File f) throws Exception
	{return (Properties) loadProp.t(f);}
	
	
	
	private class Holder implements R
	{
		private Map dirs;
		private Map cache;
		
		public Holder(Map dirs)
		{
			this.dirs = dirs;
			cache = new HashMap();
		}
		public Object r(String key) throws Exception
		{
			if(!cache.containsKey(key))
			cache.put(key,build(dirs,key));
			return cache.get(key);
		}
	}
}
