package a.entity.gus06.dir.dirtoentrymap;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140903";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		int length = dir.getAbsolutePath().length();
		
		Map map = new HashMap();
		handleDir(map,length,dir);
		return map;
	}
	
	
	private void handleDir(Map map, int length, File dir)
	{
		File[] ff = dir.listFiles();
		if(ff==null) return;
		for(File f:ff)
		{
			if(f.isDirectory()) handleDir(map,length,f);
			else handleFile(map,length,f);
		}
	}
	
	
	private void handleFile(Map map, int length, File file)
	{
		String path = file.getAbsolutePath().substring(length).replace(File.separator,"/");
		if(path.startsWith("/")) path = path.substring(1);
		map.put(path,file);
	}
}
