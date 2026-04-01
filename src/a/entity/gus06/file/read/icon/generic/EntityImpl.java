package a.entity.gus06.file.read.icon.generic;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160209";}


	private Service findReader;
	
	public EntityImpl() throws Exception
	{
		findReader = Outside.service(this,"gus06.file.read.icon.generic.findreader");
	}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.isFile())
			throw new Exception("Invalid file: "+file);
		
		try
		{
			T reader = (T) findReader.t(file);
			Object r = reader.t(file);
			return r!=null?r:"";
		}
		catch(Exception e)
		{
			String message = "Failed to read icon from file: "+file.getAbsolutePath();
			Outside.err(this,"t(Object)",new Exception(message,e));
			return "ERR:"+e;
		}
	}
}
