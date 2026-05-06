package a.entity.gus06.java.srcfile.extract.entity.infomap1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}

	private Service readFile;
	private Service extract;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		extract = Outside.service(this,"gus06.java.srccode.extract.entity.infomap1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		try
		{
			String src = (String) readFile.t(file);
			return extract.t(src);
		}
		catch(Exception e)
		{
			String message = "Failed to extract info from entity file: "+file;
			throw new Exception(message,e);
		}
		
	}
}
