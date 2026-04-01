package a.entity.gus06.dir.listing.dirtopathmap.forext;

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
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String ext = ((String) o[1]).toLowerCase();
		
		int len = dir.getAbsolutePath().length();
		
		Map map = new HashMap();
    		handleDir(len,map,dir,ext);
        	return map;
	}
	
	
	private void handleDir(int len, Map map, File dir, String ext)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isDirectory()) handleDir(len,map,f,ext);
			else if(isTarget(f,ext)) handleFile(len,map,f);
		}
	}
	
	private void handleFile(int len, Map map, File file)
	{
		String relPath = file.getAbsolutePath().substring(len);
		map.put(relPath,file);
	}
	
	
	private boolean isTarget(File f, String ext)
	{
		String name = f.getName().toLowerCase();
		return name.endsWith("."+ext);
	}
}
