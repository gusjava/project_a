package a.entity.gus06.dir.listing.dir1tomap.md5_files;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230326";}
	
	private Service buildMd5;
	
	public EntityImpl() throws Exception
	{
		buildMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = new HashMap();
		
		if(obj instanceof File)
		{
			File dir = (File) obj;
			analyze(map,dir);
		}
		else if(obj instanceof File[])
		{
			File[] dirs = (File[]) obj;
			for(File dir : dirs)
			analyze(map,dir);
		}
		else if(obj instanceof List)
		{
			List dirs = (List) obj;
			for(Object dir : dirs)
			analyze(map,(File) dir);
		}
		return map;
	}
	
	
	
	private void analyze(Map map, File p) throws Exception
	{
		if(p.isDirectory())
		{
			File[] ff = p.listFiles();
			for(File f:ff) analyze(map,f);
		}
		else if(p.isFile())
		{
			String md5 = (String) buildMd5.t(p);
			findSet(map,md5).add(p);
		}
	}
	
	
	private Set findSet(Map map, String key)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		return (Set) map.get(key);
	}
}
