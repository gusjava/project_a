package a.entity.gus06.file.read.image.jai.cache;

import a.framework.*;
import java.io.File;
import javax.media.jai.JAI;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150617";}
	
	
	private HashMap cache;
	
	public EntityImpl() throws Exception
	{cache = new HashMap();}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String path = file.getAbsolutePath();
		
		if(!cache.containsKey(path))
			cache.put(path,JAI.create("fileload",path));
		return cache.get(path); 
	}
}
