package a.entity.gus06.java.srcfile.extract.package1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140727";}


	private Service readFile;
	private Service srcToPackage;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		srcToPackage = Outside.service(this,"gus06.java.srccode.extract.package1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return srcToPackage.t(src);
	}
}
