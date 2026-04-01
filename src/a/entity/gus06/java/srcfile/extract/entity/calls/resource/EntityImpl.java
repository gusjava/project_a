package a.entity.gus06.java.srcfile.extract.entity.calls.resource;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150311";}

	private Service readFile;
	private Service srcToCalls;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		srcToCalls = Outside.service(this,"gus06.java.srccode.extract.entity.calls.resource");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return srcToCalls.t(src);
	}
}
