package a.entity.gus06.java.srcdir.retrieve.javasrc;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160410";}
	
	private Service readFile;
	private Service findFile;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		findFile = Outside.service(this,"gus.x.java.srcdir.retrieve.javafile");
	}

	
	public Object t(Object obj) throws Exception
	{
		File javaFile = (File) findFile.t(obj);
		if(javaFile==null || !javaFile.isFile()) return null;
		return readFile.t(javaFile);
	}
}
