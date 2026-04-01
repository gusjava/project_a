package a.entity.gus06.file.read.properties.generic;

import java.io.File;
import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151018";}

	private Service findReader;
	
	public EntityImpl() throws Exception
	{
		findReader = Outside.service(this,"gus06.file.read.properties.generic.findreader");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.isFile())
			throw new Exception("Invalid file: "+file);
		
		try
		{
			T reader = (T) findReader.t(file);
			Map map = (Map) reader.t(file);
			if(map==null) return new HashMap();
			return map;
		}
		catch(Exception e)
		{
			String message = "Failed to read properties from file: "+file.getAbsolutePath();
			Outside.err(this,"t(Object)",new Exception(message,e));
			
			Map map = new HashMap();
			map.put("err",message+": "+e.getMessage());
			return map;
		}
	}
}
