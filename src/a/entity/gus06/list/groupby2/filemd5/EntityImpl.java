package a.entity.gus06.list.groupby2.filemd5;

import a.framework.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180303";}


	private Service buildMd5;
	
	public EntityImpl() throws Exception
	{
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		List input = (List) obj;
		
		Map map1 = new HashMap();
		for(Object elem:input)
		{
			File file = (File) elem;
			String key = ""+file.length();
			findList(map1,key).add(file);
		}
		
		Map map2 = new HashMap();
		Iterator it = map1.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			List set = (List) map1.get(key);
			if(set.size()>1)
			{
				Map m = checkMD5(set);
				map2.putAll(m);
			}
		}
		return map2;
	}
	
	
	
	
	private List findList(Map map, String key)
	{
		if(!map.containsKey(key)) map.put(key,new ArrayList());
		return (List) map.get(key);
	}
	
	
	
	private Map checkMD5(List list) throws Exception
	{
		Map map = new HashMap();
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			File file = (File) it.next();
			String md5 = (String) buildMd5.t(file);
			findList(map,md5).add(file);
		}
		
		it = map.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			List s = (List) map.get(md5);
			if(s.size()<2) it.remove();
		}
		return map;
	}
}
