package a.entity.gus06.sys.webserver1.web3.processor2.handle;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160219";}
	
	public static final String FILENAME_INDEX = "index.gus";
	public static final String DEFAULT = "default";
	public static final String WEB = "web";
	
	public static final String KEY_REQUEST = "request";
	public static final String KEY_PATH = "path";
	public static final String KEY_OUTPUT = "output";
	
	
	
	private Service contextBuilder;
	private Service engine;

	private File storeDir;



	public EntityImpl() throws Exception
	{
		contextBuilder = Outside.service(this,"gus06.sys.script1.context.builder1");
		engine = Outside.service(this,"gus06.sys.script1.engine");
		
		storeDir = (File) Outside.resource(this,"path#path.web3.dir.spaces");
		storeDir.mkdirs();
	}
	

	
	public synchronized void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Map request = (Map) get(map,KEY_REQUEST);
		String path = path(request);
		
		try
		{
			String[] nn = path.split("/");
			String site = path.length()>0?nn[0]:DEFAULT;
			String page = nn.length>1?nn[1]:"";
		
			if(page.equals(WEB))
			{
				File resource = new File(storeDir,path.replace("/",File.separator));
				map.put(KEY_OUTPUT,resource);
				return;
			}
		
			File siteDir = new File(storeDir,site);
			File indexFile = new File(siteDir,FILENAME_INDEX);
			if(!indexFile.isFile()) throw new Exception("Index file not found: "+indexFile);
		
			Object output = get(map,KEY_OUTPUT);
			Map context = (Map) contextBuilder.t(new Object[]{map,output});
			engine.p(new Object[]{indexFile,context});
		}
		catch(Exception e)
		{
			String message = "request has failed with path: "+path;
			throw new Exception(message,e);
		}
	}
	
	
	
	
	
	private String path(Map request) throws Exception
	{
		String path = (String) get(request,KEY_PATH);
		while(path.startsWith("/")) path = path.substring(1);
		while(path.endsWith("/")) path = path.substring(0,path.length()-1);
		return path;
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
