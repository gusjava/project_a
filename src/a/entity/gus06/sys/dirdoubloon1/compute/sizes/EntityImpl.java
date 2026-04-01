package a.entity.gus06.sys.dirdoubloon1.compute.sizes;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221218";}
	
	
	public Object t(Object obj) throws Exception
	{
		List listing = (List) obj;
		Map map = new HashMap();
		for(int i=0;i<listing.size();i++)
		{
			File dir = (File) listing.get(i);
			analyze(map,dir);
		}
		return map;
	}
	
	
	private void analyze(Map map, File p)
	{
		if(p.isDirectory())
		{
			File[] ff = p.listFiles();
			for(File f:ff) analyze(map,f);
		}
		else if(p.isFile())
		{
			long size = p.length();
			findSet(map, size).add(p);
		}
	}
	
	
	private Set findSet(Map map, Object key)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		return (Set) map.get(key);
	}
}