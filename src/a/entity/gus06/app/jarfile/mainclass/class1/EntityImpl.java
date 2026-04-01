package a.entity.gus06.app.jarfile.mainclass.class1;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180129";}



	

	private Service findMainclass;

	
	public EntityImpl() throws Exception
	{
		findMainclass = Outside.service(this,"gus06.app.jarfile.mainclass");
	}
	
	
	public Object g() throws Exception
	{
		String classpath = (String) findMainclass.g();
		return Class.forName(classpath);
	}
}
