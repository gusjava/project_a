package a.entity.gus06.java.srcfile.extract.manager.ismanager;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141110";}

	private Service readFile;
	private Service isManager;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		isManager = Outside.service(this,"gus06.java.srccode.extract.manager.ismanager");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return isManager.f(src);
	}
}
