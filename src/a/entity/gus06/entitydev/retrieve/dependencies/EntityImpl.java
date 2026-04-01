package a.entity.gus06.entitydev.retrieve.dependencies;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150823";}


	private Service retrieveFile;
	private Service extract;


	public EntityImpl() throws Exception
	{
		retrieveFile = Outside.service(this,"gus06.entitydev.retrieve.javafile1");
		extract = Outside.service(this,"gus06.java.srcfile.extract.entity.dependencies");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String entityName = (String) obj;
		File file = (File) retrieveFile.t(entityName);
		return extract.t(file);
	}
}
