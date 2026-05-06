package a.entity.gus06.java.srcfile.extract.entity.dependencies1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150823";}

	private Service readFile;
	private Service extract;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		extract = Outside.service(this,"gus06.java.srccode.extract.entity.dependencies1");
	}
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return extract.t(src);
	}
}