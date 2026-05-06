package a.entity.gus06.java.srcfile.extract.framework.isframework;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141110";}

	private Service readFile;
	private Service isFramework;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		isFramework = Outside.service(this,"gus06.java.srccode.extract.framework.isframework");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return isFramework.f(src);
	}
}
