package a.entity.gus06.file.mime.nio.detect;

import a.framework.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161128";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return detectFromFile((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String detectFromFile(File f) throws IOException
	{
		Path source = f.toPath();
    		return Files.probeContentType(source);
	}
}
