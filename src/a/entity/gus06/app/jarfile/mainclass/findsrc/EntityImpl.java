package a.entity.gus06.app.jarfile.mainclass.findsrc;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140916";}



	

	private Service findMainclass;
	private Service classpathToSrc;

	
	public EntityImpl() throws Exception
	{
		findMainclass = Outside.service(this,"gus06.app.jarfile.mainclass");
		classpathToSrc = Outside.service(this,"gus06.app.jarfile.classpath.findsrc");
	}
	
	
	public Object g() throws Exception
	{
		String classpath = (String) findMainclass.g();
		return classpathToSrc.t(classpath);
	}
}
