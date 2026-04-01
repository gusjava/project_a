package a.entity.gus06.dir.listing.dirtomap.doubloon.md5;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150409";}


	private Service size_files;
	private Service buildMd5;
	private Service keepMulti;


	public EntityImpl() throws Exception
	{
		size_files = Outside.service(this,"gus06.dir.listing.dirtomap.size_files");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		keepMulti = Outside.service(this,"gus06.map.setmap.keepmulti");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map1 = (Map) size_files.t(dir);
		Map map2 = new HashMap();
		
		Iterator it1 = map1.keySet().iterator();
		while(it1.hasNext())
		{
			String key = (String) it1.next();
			Set set = (Set) map1.get(key);
			if(set.size()>1)
			{
				Iterator it2 = set.iterator();
				while(it2.hasNext())
				{
					File f = (File) it2.next();
					String md5 = (String) buildMd5.t(f);
					findSet(map2,md5).add(f);
				}
			}
		}
		
		keepMulti.p(map2);
		return map2;
	}
	
	
	private Set findSet(Map map, String key)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		return (Set) map.get(key);
	}
}
