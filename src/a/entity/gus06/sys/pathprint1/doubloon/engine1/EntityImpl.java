package a.entity.gus06.sys.pathprint1.doubloon.engine1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230327";}


	private Service totalSize;

	public EntityImpl() throws Exception
	{
		totalSize = Outside.service(this,"gus06.sys.pathprint1.totalsize");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map m = (Map) totalSize.t(obj);
		Map sizeMap = buildSizeMap(m);
		
		Iterator it = sizeMap.keySet().iterator();
		while(it.hasNext())
		{
			Long size = (Long) it.next();
			Set files = (Set) sizeMap.get(size);
			reduceSet(files);
		}
		
		return m;
	}
	
	
	private Map buildSizeMap(Map m)
	{
		Map sizeMap = new HashMap();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			File f = (File) it.next();
			Long size = (Long) m.get(f);
			add(sizeMap, size, f);
		}
		return sizeMap;
	}
	
	
	private void add(Map map, Object key, Object value)
	{
		if(!map.containsKey(key)) map.put(key, new HashSet());
		((Set) map).add(value);
	}
	
	
	private void reduceSet(Set files)
	{
		Iterator it = files.iterator();
		while(it.hasNext())
		{
			File f = (File) it.next();
		}
	}
}
