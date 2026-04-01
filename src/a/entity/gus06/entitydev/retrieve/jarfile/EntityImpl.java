package a.entity.gus06.entitydev.retrieve.jarfile;

import java.io.File;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140730";}

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String entityName = get1(map,SRC.KEY_ENTITYNAME);
		String rootDir = get1(map,SRC.KEY_ROOTDIR);
		
		return new File(rootDir,entityName+".jar");
	}
	
	
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key))
			throw new Exception("Key not found inside map: "+key);
		return (String) map.get(key);
	}
}
