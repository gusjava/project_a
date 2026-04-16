package a.entity.gus06.file.jar.entry.topackage;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}



	private Service toClasspath;
	private Service toPackage;

	public EntityImpl() throws Exception
	{
		toClasspath = Outside.service(this,"gus.x.file.jar.entry.toclasspath");
		toPackage = Outside.service(this,"gus06.java.classpath.topackage");
	}
	
	public Object t(Object obj) throws Exception
	{
		String classpath = (String) toClasspath.t(obj);
		return toPackage.t(classpath);
	}
}
