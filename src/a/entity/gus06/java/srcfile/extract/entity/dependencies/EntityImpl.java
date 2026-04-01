package a.entity.gus06.java.srcfile.extract.entity.dependencies;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150312";}

	private Service readFile;
	private Service extract;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		extract = Outside.service(this,"gus06.java.srccode.extract.entity.dependencies");
	}
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return extract.t(src);
	}
}