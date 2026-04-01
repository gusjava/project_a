package a.entity.gus06.dir.listing.dirtomap.file_extmap;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161117";}


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
	
	
	private Object handle(File file, Map map) throws Exception
	{
		Object value = computeValue(file,map);
		if(value!=null) map.put(file,value);
		return value;
	}
	
	
	
	private Object computeValue(File file, Map map) throws Exception
	{
		if(!file.exists()) return null;
		if(file.isFile()) return computeValue_file(file);
		return computeValue_dir(file,map);
	}
	
	
	private Object computeValue_file(File file) throws Exception
	{
		return (String) getExt.t(file);
	}
	
	
	private Object computeValue_dir(File dir, Map map) throws Exception
	{
		Map value = new HashMap();
		
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			Object value0 = handle(f,map);
			mergeValues(value,value0);
		}
		return value;
	}
	
	
	
	
	
	private void mergeValues(Map value, Object value0)
	{
		if(value0 instanceof String)
		{
			increaseNumber(value, (String) value0, 1);
			increaseNumber(value, "*", 1);
			return;
		}
		
		Map value0m = (Map) value0;
		Iterator it = value0m.keySet().iterator();
		while(it.hasNext())
		{
			String ext = (String) it.next();
			Integer n = (Integer) value0m.get(ext);
			increaseNumber(value, ext, n.intValue());
		}
	}
	
	private void increaseNumber(Map map, String ext, int count)
	{
		if(!map.containsKey(ext))
		{
			map.put(ext,Integer.valueOf(count));
			return;
		}
		Integer n0 = (Integer) map.get(ext);
		map.put(ext,Integer.valueOf(count+n0.intValue()));
	}
}
