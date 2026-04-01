package a.entity.gus06.dir.listing.dirtomap.relpath_file;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180424";}

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		int len = dir.getAbsolutePath().length()+1;
		
		Map map = new HashMap();
    		handleDir(map,dir,len);
        	return map;
	}
	
	
	private void handleDir(Map map, File dir, int len)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			String relPath = f.getAbsolutePath().substring(len);
			map.put(relPath,f);
			
			if(f.isDirectory())
			{
				handleDir(map,f,len);
			}
		}
	}
}
