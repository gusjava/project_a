package a.entity.gus06.dir.listing.dirtomap.file_size;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161116";}


	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map = new HashMap();
		handle(dir,map);
		return map;
	}
	
	
	
	private long handle(File file, Map map)
	{
		long size = 0;
		if(file.isDirectory())
		{
			File[] ff = file.listFiles();
			if(ff!=null) for(File f:ff)
			size += handle(f,map);
		}
		else if(file.isFile())
		{
			size = file.length();
		}
		map.put(file,Long.valueOf(size));
		return size;
	}
}
