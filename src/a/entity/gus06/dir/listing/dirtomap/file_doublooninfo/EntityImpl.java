package a.entity.gus06.dir.listing.dirtomap.file_doublooninfo;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220715";}


	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map = new HashMap();
		handleDir(dir, map);
		return map;
	}
	
	
	private void handle(File file, Map map)
	{
		if(file.isDirectory()) handleDir(file, map);
		else if(file.isFile()) handleFile(file, map);
	}
	
	
	private void handleDir(File dir, Map map)
	{
		Map m = new HashMap();
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff) handle(f, map);
	}
	
	
	private void handleFile(File file, Map map)
	{
		Map m = new HashMap();
		Long length = Long.valueOf(file.length());
		m.put("length",length);
	}
}