package a.entity.gus06.file.image.extraction.jpegphoto.originaltime.cache;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}
	
	
	private Service find;
	private Map cache;
	
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.file.image.extraction.jpegphoto.originaltime");
		cache = new HashMap();
	}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		String path = f.getAbsolutePath();
		
		if(!cache.containsKey(path))
			cache.put(path,find.t(f));
		return cache.get(path);
	}
}
