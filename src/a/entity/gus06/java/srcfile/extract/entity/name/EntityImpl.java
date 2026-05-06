package a.entity.gus06.java.srcfile.extract.entity.name;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140727";}

	private Service readFile;
	private Service srcToName;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		srcToName = Outside.service(this,"gus06.java.srccode.extract.entity.name");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return srcToName.t(src);
	}
}
