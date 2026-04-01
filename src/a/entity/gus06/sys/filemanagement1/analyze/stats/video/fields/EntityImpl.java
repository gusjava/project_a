package a.entity.gus06.sys.filemanagement1.analyze.stats.video.fields;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201105";}

	private Service retrieveMd5Set;
	private Service mapToString;
	
	public EntityImpl() throws Exception
	{
		retrieveMd5Set = Outside.service(this,"gus06.sys.filemanagement1.tool.video.retrieve.md5set");
		mapToString = Outside.service(this,"gus06.tostring.map.tn");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		// RETRIEVE MD5 SET
		
		Set md5Set = (Set) retrieveMd5Set.t(engine);
		
		p.println("Detected videos: "+md5Set.size());
		p.println("______________");
		
		if(md5Set.isEmpty()) return;
		
		Map map = new HashMap();
		Iterator it = md5Set.iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Map prop = (Map) ((R)engine).r("prop:"+md5);
			if(prop!=null)
			{
				Iterator it2 = prop.keySet().iterator();
				while(it2.hasNext())
				{
					String key = (String) it2.next();
					if(key.startsWith("video."))
					increase(map,key);
				}
			}
		}
		
		String summary = (String) mapToString.t(map);
		p.println(summary);
	}
	
	
	
	
	private void increase(Map map, String key)
	{
		if(!map.containsKey(key)) map.put(key,Integer.valueOf(1));
		else
		{
			Integer n = (Integer) map.get(key);
			map.put(key,Integer.valueOf(n.intValue()+1));
		}
	}
}