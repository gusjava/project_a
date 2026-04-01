package a.entity.gus06.sys.filemapper1.idtoscript.zip;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}
	
	public static final String KEY_SRC = "src";
	public static final String KEY_LOCATION = "location";
	
	private Service buildZipFile;
	private Service isToString;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		isToString = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map root = (Map) o[0];
		String main = (String) o[1];
		String id = (String) o[2];
		
		File src = (File) get1(root,KEY_SRC);
		String location = (String) get0(root,KEY_LOCATION);
		return idToScript(src,location,main,id);
	}
	
	
	
	private String idToScript(File zip, String location, String main, String id) throws Exception
	{
		if(zip==null || !zip.isFile()) throw new Exception("Zip file undefined: " + zip);
		if(id==null) throw new Exception("Id undefined: null");
		
		ZipFile zipFile = (ZipFile) buildZipFile.t(zip);
		String entryName = buildEntryName(id,location,main);
		
		ZipEntry zipEntry = zipFile.getEntry(entryName);
		if(zipEntry==null) throw new Exception("ZipEntry not found: "+entryName+" for file "+zip);
		
		InputStream is = zipFile.getInputStream(zipEntry);
		String script = (String) isToString.t(is);
		is.close();
		zipFile.close();
		
		return script;
	}
	
	
	private String buildEntryName(String id, String location, String main)
	{
		if(main==null) main = "script.gus";
		
		String path = id.replace(".","/");
		if(!path.endsWith("/")) path = path+"/";
		
		if(location==null) return path+main;
		
		if(!location.endsWith("/")) location = location+"/";
		return location+path+main;
	}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}