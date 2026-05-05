package a.entity.gus06.sys.filemanagement1.tool.rootmap.loadall;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191110";}


	private Service readProp;
	private Service getName0;

	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus.x.file.prop.read");
		getName0 = Outside.service(this,"gus.x.file.getname0");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map = new HashMap();
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f : ff)
		{
			Map prop = (Map) readProp.t(f);
			String name = (String) getName0.t(f);
			map.put(name,prop);
		}
		return map;
	}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return (String) map.get(key);
	}
}