package a.entity.gus06.dir.listing.dirtomap.file_fnumber;

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
	
	
	
	private int handle(File file, Map map)
	{
		if(!file.exists()) return 0;
		if(file.isFile()) return 1;
		
		int nb = 0;
		File[] ff = file.listFiles();
		if(ff!=null) for(File f:ff)
		nb += handle(f,map);
		map.put(file,Integer.valueOf(nb));
		return nb;
	}
}
