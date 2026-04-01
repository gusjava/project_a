package a.entity.gus06.appli.appliaccess.engine.download.handlemd5;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150318";}


	public static final String KEY_STOREDIR = "storedir";
	public static final String KEY_NAME = "name";
	public static final String KEY_VERSION = "version";
	
	
	private Service sender;
	private Service downloader;
	private Service buildMd5;
	private Service getFile;
	
	
	
	public EntityImpl() throws Exception
	{
		sender = Outside.service(this,"gus06.appli.appliaccess.api.sender");
		downloader = Outside.service(this,"gus06.appli.appliaccess.api.downloader");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		getFile = Outside.service(this,"gus06.sys.option.getfile");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String md5 = (String) obj;
		String info = "retrieving "+md5;
		
		try
		{
			Map infos = retrieveInfos(md5);
			File file = retrieveJar(md5);
			
			String md5_ = (String) buildMd5.t(file);
			if(!md5.equals(md5_)) throw new Exception("Corrupted data was received (new MD5="+md5_+")");
		
			File dir = targetDir(infos);
			dir.mkdirs();
			
			File file1 = new File(dir,file.getName());
			file.renameTo(file1);
			
			return info+"\n"+file1;
		}
		catch(Exception e)
		{
			Outside.err(this,"t(Object)",e);
			
			String m1 = "Download failed for "+md5;
			String m2 = "Exception: "+e.getMessage();
			return info+"\n"+m1+"\n"+m2;
		}
	}
	
	
	
	
	private Map retrieveInfos(String md5) throws Exception
	{
		try
		{
			Map input = new HashMap();
			input.put("action","infos:jar");
			input.put("md5",md5);
		
			String result = (String) sender.t(input);
			if(result.startsWith("error:")) throw new Exception("Server "+result);
			return stringToMap(result);
		}
		catch(Exception e)
		{throw new Exception("Retrieving infos failed for md5: "+md5,e);}
	}
	
	
	
	
	
	private File retrieveJar(String md5) throws Exception
	{
		try
		{
			Map input = new HashMap();
			input.put("action","retrieve:jar");
			input.put("md5",md5);
		
			return (File) downloader.t(input);
		}
		catch(Exception e)
		{throw new Exception("Retrieving jar failed for md5: "+md5,e);}
	}
	
	
	
	
	private Map stringToMap(String s)
	{
		Map map = new HashMap();
		String[] nn = s.trim().split("\n");
		for(String n:nn)
		{
			String[] k = n.trim().split(":",2);
			map.put(k[0],k[1]);
		}
		return map;
	}
	
	
	
	
	
	private File targetDir(Map infos) throws Exception
	{
		File storeDir = (File) getFile.r(KEY_STOREDIR);
		String name = get(infos,KEY_NAME);
		String version = get(infos,KEY_VERSION);
		
		File d = new File(storeDir,name);
		d = new File(d,version);
		return new File(d,"jar");
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Unknown key: "+key);
		return (String) map.get(key);
	}
}
