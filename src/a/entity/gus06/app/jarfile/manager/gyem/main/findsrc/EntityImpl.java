package a.entity.gus06.app.jarfile.manager.gyem.main.findsrc;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140917";}



	private Service classpathToSrc;

	
	public EntityImpl() throws Exception
	{
		classpathToSrc = Outside.service(this,"gus06.app.jarfile.classpath.findsrc");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return "";
		String classpath = "gus06.manager.gus.gyem."+obj;
		return classpathToSrc.t(classpath);
	}
}
