package a.entity.gus06.dir.properties.count.fields;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201021";}


	private Service listing;
	private Service readProp;
	
	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus.x.file.prop.read");
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		File[] ff = (File[]) listing.t(dir);
		
		Map map = new HashMap();
		
		for(File f:ff)
		{
			Map prop = (Map) readProp.t(f);
			countField(map,prop);
		}
		
		return map;
	}
	
	
	
	private void countField(Map map, Map prop)
	{
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			increase(map,key);
		}
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
