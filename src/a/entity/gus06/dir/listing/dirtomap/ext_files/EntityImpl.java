package a.entity.gus06.dir.listing.dirtomap.ext_files;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161118";}


	private Service getExt;

	public EntityImpl() throws Exception
	{
		getExt = Outside.service(this,"gus06.file.getextension.lowercase");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map = new HashMap();
		analyze(map,dir);
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
			String key = (String) getExt.t(p);
			findSet(map,key).add(p);
		}
	}
	
	
	private Set findSet(Map map, String key)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		return (Set) map.get(key);
	}
}
