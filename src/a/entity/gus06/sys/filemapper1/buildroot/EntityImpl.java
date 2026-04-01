package a.entity.gus06.sys.filemapper1.buildroot;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}
	
	public static final String KEY_SRC = "src";
	public static final String KEY_TYPE = "type";
	
	public static final String TYPE_DIR = "dir";
	public static final String TYPE_ZIP = "zip";
	
	
	public Object t(Object obj) throws Exception
	{return rootAsMap(obj);}
	
	
	private Map rootAsMap(Object obj) throws Exception
	{
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof File) return fileRootAsMap((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Map fileRootAsMap(File f) throws Exception
	{
		if(!f.exists()) throw new Exception("Root not found: "+f);
		if(f.isFile()) return buildZipRoot(f);
		if(f.isDirectory()) return buildDirRoot(f);
		throw new Exception("Invalid root: "+f);
	}
	
	
	private Map buildZipRoot(File f) throws Exception
	{
		Map map = new HashMap();
		map.put(KEY_SRC,f);
		map.put(KEY_TYPE,TYPE_ZIP);
		return map;
	}
	
	private Map buildDirRoot(File f) throws Exception
	{
		Map map = new HashMap();
		map.put(KEY_SRC,f);
		map.put(KEY_TYPE,TYPE_DIR);
		return map;
	}
}