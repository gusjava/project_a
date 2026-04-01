package a.entity.gus06.java.srcfile.extract.gyem.build;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140906";}

	private Service readFile;
	private Service srcToBuild;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		srcToBuild = Outside.service(this,"gus06.java.srccode.extract.gyem.build");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return srcToBuild.t(src);
	}
}
