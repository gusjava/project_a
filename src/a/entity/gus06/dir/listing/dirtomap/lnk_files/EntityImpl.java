package a.entity.gus06.dir.listing.dirtomap.lnk_files;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}


	private Service extract;
	
	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.file.lnk.extract.path");
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
		else if(isLnkFile(p))
		{
			String key = (String) extract.t(p);
			findSet(map,key).add(p);
		}
	}
	
	
	private Set findSet(Map map, String key)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		return (Set) map.get(key);
	}
	
	
	private boolean isLnkFile(File p)
	{return p.isFile() && p.getName().toLowerCase().endsWith(".lnk");}
}
