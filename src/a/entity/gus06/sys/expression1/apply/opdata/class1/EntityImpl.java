package a.entity.gus06.sys.expression1.apply.opdata.class1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180203";}


	private Service opToClasspath;

	public EntityImpl() throws Exception
	{
		opToClasspath = Outside.service(this,"gus06.sys.expression1.apply.opdata.classpath");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String classpath = (String) opToClasspath.t(obj);
		return Class.forName(classpath);
	}
}