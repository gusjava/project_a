package a.entity.gus06.java.srccode.extract.entity.name;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20140827";}

	private Service srcToPackage;
	
	public EntityImpl() throws Exception
	{
		srcToPackage = Outside.service(this,"gus06.java.srccode.extract.package1");
	}
	
	public Object t(Object obj) throws Exception
	{
		String packageName = (String) srcToPackage.t(obj);
		if(packageName.startsWith("a.entity."))
			return packageName.substring(9);
		return null;
	}
}
