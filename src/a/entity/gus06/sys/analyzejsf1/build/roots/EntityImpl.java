package a.entity.gus06.sys.analyzejsf1.build.roots;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190308";}
	
	
	public static final String KEY0_CONF = "conf";
	public static final String KEY0_ROOTS = "roots";
	
	
	public static final String KEY1_ROOT = "root";
	
	public static final String KEY1_SRC = "src";
	public static final String KEY1_BIN = "bin";
	public static final String KEY1_PROD = "prod";
	public static final String KEY1_GIT = "git";
	public static final String KEY1_GRADLE = "gradle";
	public static final String KEY1_SETTINGS = "settings";
	
	public static final String KEY1_JAVA = "java";
	public static final String KEY1_RESOURCES = "resources";
	public static final String KEY1_WEBAPP = "webapp";
	public static final String KEY1_WEBINF = "webinf";
	
	public static final String KEY1_MIGRATION = "migration";
	

	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Map m = new HashMap();
		map.put(KEY0_ROOTS,m);
		
		handle(map,m);
	}
	
	
	
	private void handle(Map map, Map m) throws Exception
	{
		Map conf = (Map) get(map,KEY0_CONF);
		if(conf==null) throw new Exception("Conf not found inside map");
		
		File root = (File) get(conf,KEY1_ROOT);
		if(root==null) throw new Exception("Root not found inside conf");
		
		if(!root.isDirectory()) throw new Exception("Invalid root: "+root);
		m.put(KEY1_ROOT,root);
		
		putRoot(m,conf,KEY1_SRC,root,		"src");
		putRoot(m,conf,KEY1_BIN,root,		"bin");
		putRoot(m,conf,KEY1_PROD,root,		"prod");
		putRoot(m,conf,KEY1_GIT,root,		".git");
		putRoot(m,conf,KEY1_GRADLE,root,	".gradle");
		putRoot(m,conf,KEY1_SETTINGS,root,	".settings");
		
		putRoot(m,conf,KEY1_JAVA,root,		"src/main/java");
		putRoot(m,conf,KEY1_RESOURCES,root,	"src/main/resources");
		putRoot(m,conf,KEY1_WEBAPP,root,	"src/main/webapp");
		putRoot(m,conf,KEY1_WEBINF,root,	"src/main/webapp/WEB-INF");
		putRoot(m,conf,KEY1_MIGRATION,root,	"src/main/resources/db/migration");
		
	}
	
	
	private Object get(Map m, String key)
	{return m.containsKey(key) ? m.get(key) : null;}
	
	
	private void putRoot(Map m, Map conf, String key1, File root, String defaultPath) throws Exception
	{
		File f = buildFile(conf,key1,root,defaultPath);
		m.put(key1,f);
	}
	
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