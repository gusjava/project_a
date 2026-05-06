package a.entity.gus06.entitydev.retrieve.srccode;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140713";}


	private Service retrieveFile;
	private Service readFile;
	
	
	public EntityImpl() throws Exception
	{
		retrieveFile = Outside.service(this,"gus06.entitydev.retrieve.javafile");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) retrieveFile.t(obj);
		if(file==null || !file.exists()) return null;
		return readFile.t(file);
	}
}
