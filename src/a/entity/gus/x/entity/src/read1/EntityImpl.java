package a.entity.gus.x.entity.src.read1;

import java.io.File;
import a.framework.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260414";}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
		return content.replace(System.lineSeparator(), "\n");
	}
}