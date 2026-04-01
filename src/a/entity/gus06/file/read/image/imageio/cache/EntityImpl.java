package a.entity.gus06.file.read.image.imageio.cache;

import a.framework.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141122";}
	
	
	private HashMap cache;
	
	public EntityImpl() throws Exception
	{cache = new HashMap();}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String path = file.getAbsolutePath();
		
		if(!cache.containsKey(path))
			cache.put(path,ImageIO.read(file));
		return cache.get(path); 
	}
}
