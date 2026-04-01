package a.entity.gus06.dir.listing.dirtomap.ext_fnumber;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

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
		handle(dir,map);
		return map;
	}
	
	
	
	private void handle(File file, Map map) throws Exception
	{
		if(file.isDirectory())
		{
			File[] ff = file.listFiles();
			if(ff!=null) for(File f:ff) handle(f,map);
		}
		else if(file.isFile())
		{
			String ext = (String) getExt.t(file);
			add(map,ext);
		}
	}
	
	
	
	private void add(Map map, String ext)
	{
		if(!map.containsKey(ext))
		{map.put(ext,Integer.valueOf(1));return;}
		
		Integer n = (Integer) map.get(ext);
		map.put(ext,Integer.valueOf(n.intValue()+1));
	}
}
