package a.entity.gus06.entitydev.refactor.remove1;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140716";}


	private Service remove;
	private File rootDir;
	
	public EntityImpl() throws Exception
	{
		remove = Outside.service(this,"gus06.entitydev.refactor.remove");
		rootDir = (File) Outside.resource(this,"path#path.dev.entityroot");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = toMap(obj);
		
		if(rootDir==null)
			throw new Exception("Unknown root path for entity src code");
		map.put(SRC.KEY_ROOTDIR,rootDir.getAbsolutePath());
		
		remove.p(map);
	}
	
	
	
	
	private Map toMap(Object obj) throws Exception
	{
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof String)
		{
			Map map = new HashMap();
			map.put(SRC.KEY_ENTITYNAME,obj);
			return map;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
