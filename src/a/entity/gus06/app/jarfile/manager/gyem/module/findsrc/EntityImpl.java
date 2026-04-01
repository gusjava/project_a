package a.entity.gus06.app.jarfile.manager.gyem.module.findsrc;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140916";}



	

	private Service nameToClasspath;
	private Service classpathToSrc;

	
	public EntityImpl() throws Exception
	{
		nameToClasspath = Outside.service(this,"gus06.app.manager.gyem.module.nametoclasspath");
		classpathToSrc = Outside.service(this,"gus06.app.jarfile.classpath.findsrc");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return "";
		String classpath = (String) nameToClasspath.t(obj);
		return classpathToSrc.t(classpath);
	}
}
