package a.entity.gus06.sys.analyzejsf1.scan.resourcesdir;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190308";}

	public static final String KEY0_CONF = "conf";
	public static final String KEY0_ROOTS = "roots";
	public static final String KEY0_RESOURCES = "resources";
	
	public static final String KEY1_RESOURCES = "resources";
	
	public static final String KEY1_ENV_FILE = "env-file";
	public static final String KEY1_CONF_FILE = "conf-file";
	public static final String KEY1_MESSAGES_FILE = "messages-file";
	
	public static final String KEY1_ENV_PROP = "env-prop";
	public static final String KEY1_CONF_PROP = "conf-prop";
	public static final String KEY1_MESSAGES_PROP = "messages-prop";
	

	private Service readProp;

	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus06.file.read.properties");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map m = new HashMap();
		map.put(KEY0_RESOURCES,m);
		
		handle(map,m);
	}
	
	
	
	private void handle(Map map, Map m) throws Exception
	{
		Map conf = (Map) get(map,KEY0_CONF);
		if(conf==null) throw new Exception("Conf not found inside map");
		
		Map roots = (Map) get(map,KEY0_ROOTS);
		if(roots==null) throw new Exception("Roots not found inside map");
		
		File root = (File) roots.get(KEY1_RESOURCES);
		
		File envFile = buildFile(conf, KEY1_ENV_FILE, root, "env.properties");
		File confFile = buildFile(conf, KEY1_CONF_FILE, root, "application.properties");
		File messagesFile = buildFile(conf, KEY1_MESSAGES_FILE, root, "messages_fr.properties");
		
		Map envProp = (Map) readProp.t(envFile);
		Map confProp = (Map) readProp.t(confFile);
		Map messagesProp = (Map) readProp.t(messagesFile);
		
		m.put(KEY1_ENV_FILE,envFile);
		m.put(KEY1_CONF_FILE,confFile);
		m.put(KEY1_MESSAGES_FILE,messagesFile);
		
		m.put(KEY1_ENV_PROP,envProp);
		m.put(KEY1_CONF_PROP,confProp);
		m.put(KEY1_MESSAGES_PROP,messagesProp);
	}
	
	
	
	private Object get(Map m, String key)
	{return m.containsKey(key) ? m.get(key) : null;}
	
	
	private File buildFile(Map conf, String key1, File root, String defaultPath) throws Exception
	{
		if(!conf.containsKey(key1))
			return new File(root,defaultPath);
			
		Object v = conf.get(key1);
		if(v instanceof File) return (File) v;
		if(v instanceof String) return new File(root,(String) v);
		
		throw new Exception("Unsupported type: "+v.getClass().getName());
	}
}
