package a.entity.gus06.y.entitydev1.retrieve.dependencies;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251110";}

	private Service retrieveFile;
	private Service extract;

	public EntityImpl() throws Exception
	{
		retrieveFile = Outside.service(this,"gus06.y.entitydev1.retrieve.javafile");
		extract = Outside.service(this,"gus06.java.srcfile.extract.entity.dependencies");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) retrieveFile.t(obj);
		return extract.t(file);
	}
}