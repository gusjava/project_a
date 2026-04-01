package a.entity.gus06.jdbc.connection.builder.get.map;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260110";}

	public static final String KEY_URL = "url";
	public static final String KEY_FILE = "file";
	public static final String KEY_SRC = "src";
	
	public static final String KEY_USER = "user";
	public static final String KEY_PWD = "pwd";

	private Service fromStringArray;
	private Service fromFile;

	public EntityImpl() throws Exception
	{
		fromStringArray = Outside.service(this,"gus06.jdbc.connection.builder.get.stringarray");
		fromFile = Outside.service(this,"gus06.jdbc.connection.builder.get.file");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Object src = get0(map,KEY_SRC);
		String user = (String) get0(map,KEY_USER);
		String pwd = (String) get0(map,KEY_PWD);
		
		if(src!=null && src instanceof String)
			return fromStringArray.t(new String[]{(String) src, user, pwd});
			
		if(src!=null && src instanceof File)
			return fromFile.t(new Object[]{(File) src, user, pwd});
			
		String url = (String) get0(map,KEY_URL);
		if(url!=null) return fromStringArray.t(new String[]{url, user, pwd});
			
		File file = (File) get0(map,KEY_FILE);
		if(file!=null) return fromFile.t(new Object[]{file, user, pwd});
		
		throw new Exception("Invalid map for cx building");
	}
	
	private Object get0(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
