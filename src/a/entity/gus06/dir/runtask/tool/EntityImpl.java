package a.entity.gus06.dir.runtask.tool;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150618";}
	
	public static final String KEY_RUNTASK = "runtask";


	private Service unique;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		unique = Outside.service(this,"entityunique");
		readFile = Outside.service(this,"gus.x.file.prop.read");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File toolFile = new File(dir.getAbsolutePath()+".tool");
		if(!toolFile.exists()) return;
		
		Map map = (Map) readFile.t(toolFile);
		String value = get(map,KEY_RUNTASK);
		P p = (P) unique.t(value);
		
		p.p(new Object[]{map,dir,progress,interrupt});
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key))
			throw new Exception("Key not found inside tool: "+key);
		return (String) map.get(key);
	}
}
