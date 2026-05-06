package a.entity.gus.x.file.string.read.utf8.n;

import java.io.File;
import a.framework.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251116";}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f==null || !f.isFile()) return null;
		
		String content = Files.readString(f.toPath(), StandardCharsets.UTF_8);
		return content.replace(System.lineSeparator(), "\n");
	}
}