package a.entity.gus06.java.srccode.extract.manager.ismanager;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141110";}

	private Service srcToPackage;
	
	public EntityImpl() throws Exception
	{
		srcToPackage = Outside.service(this,"gus06.java.srccode.extract.package1");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String packageName = (String) srcToPackage.t(obj);
		return packageName.startsWith("gus06.manager.");
	}
}
