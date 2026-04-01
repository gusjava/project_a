package a.entity.gus06.dir.listing.dirtopathmap;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170401";}

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		int len = dir.getAbsolutePath().length();
		
		Map map = new HashMap();
    		handleDir(len,map,dir);
        	return map;
	}
	
	
	private void handleDir(int len, Map map, File dir)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isDirectory()) handleDir(len,map,f);
			else handleFile(len,map,f);
		}
	}
	
	private void handleFile(int len, Map map, File file)
	{
		String relPath = file.getAbsolutePath().substring(len);
		map.put(relPath,file);
	}
}
