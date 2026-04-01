package a.entity.gus06.sys.webserver1.web1.processor.findfile;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}

	public static final String KEY_URL = "url";
	
	private File rootDir;

	public EntityImpl() throws Exception
	{
		rootDir = (File) Outside.resource(this,"path#path.web1.root");
		rootDir.mkdirs();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String url = (String) get(map,KEY_URL);
		
		if(url.startsWith("/")) url = url.substring(1);
		url = url.replace("/",File.separator);
		
		File file = new File(rootDir,url);
		if(file.isFile()) return file;
		if(file.isDirectory()) return findIndex(file);
		
		return null;
	}
	
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	
	
	private File findIndex(File dir)
	{
		File f = new File(dir,"index.html");
		if(f.isFile()) return f;
		
		f = new File(dir,"index.htm");
		if(f.isFile()) return f;
		
		f = new File(dir,"index.php");
		if(f.isFile()) return f;
		
		return null;
	}
}
