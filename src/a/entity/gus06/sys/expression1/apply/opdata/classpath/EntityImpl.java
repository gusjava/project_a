package a.entity.gus06.sys.expression1.apply.opdata.classpath;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180203";}


	private Service nameToClasspath;

	public EntityImpl() throws Exception
	{
		nameToClasspath = Outside.service(this,"gus06.app.entity.nametoclasspath");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String op = (String) obj;
		String entityName = "gus.sys.expression1.apply.op."+op;
		return nameToClasspath.t(entityName);
	}
}