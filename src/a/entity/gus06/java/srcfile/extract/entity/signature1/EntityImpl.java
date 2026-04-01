package a.entity.gus06.java.srcfile.extract.entity.signature1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}

	private Service readFile;
	private Service srcToName;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		srcToName = Outside.service(this,"gus06.java.srccode.extract.entity.signature1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return srcToName.t(src);
	}
}
