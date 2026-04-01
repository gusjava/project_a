package a.entity.gus06.dir.info.freqmap.extensions;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250607";}


	private Service getExtension;

	public EntityImpl() throws Exception
	{
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
	}
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map = new HashMap();
		handleDir(map,dir);
		return map;
	}
	
	private void handleDir(Map map, File dir) throws Exception
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isFile()) handleFile(map,f);
			else if(f.isDirectory()) handleDir(map,f);
		}
	}
	
	private void handleFile(Map map, File file) throws Exception
	{
		String extension = (String) getExtension.t(file);
		if(!map.containsKey(extension)) map.put(extension, 1);
		else
		{
			Integer previousCount = (Integer) map.get(extension);
			map.put(extension, previousCount+1);
		}
	}
}
