package a.entity.gus06.file.read.raw.j7;

import a.framework.*;
import java.io.File;
import java.nio.file.Files;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160526";}

	
	
	public Object t(Object obj) throws Exception
	{return read((File) obj);}
	
	
	
	private byte[] read(File file) throws Exception
	{
		return Files.readAllBytes(file.toPath());
	}
}
