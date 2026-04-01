package a.entity.gus06.file.ext.mimetype;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import a.framework.*;


public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20180410";}

	
	private Service fileToMime;
	private Service fileSample;
	
	private Map cache;
	
	
	public EntityImpl() throws Exception
	{
		fileToMime = Outside.service(this,"gus06.file.mime.tika.detect.asstring");
		fileSample = Outside.service(this,"gus06.file.ext.filesample");
		
		cache = new HashMap();
	}
	
	
	public Object r(String key) throws Exception
	{return t(key);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String ext = (String) obj;
		if(!cache.containsKey(ext))
			cache.put(ext,build(ext));
		return cache.get(ext);
	}
	
	
	private String build(String ext) throws Exception
	{
		File file = (File) fileSample.t(ext);
		return (String) fileToMime.t(file);
	}
}
