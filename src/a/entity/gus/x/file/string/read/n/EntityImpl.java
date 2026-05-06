package a.entity.gus.x.file.string.read.n;

import java.io.File;
import java.nio.file.Files;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240712";}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f==null || !f.isFile()) return null;
		
		String content = Files.readString(f.toPath());
		return content.replace(System.lineSeparator(), "\n");
	}
}
