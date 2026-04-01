package a.entity.gus06.file.read.properties.from.prop;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151018";}


	private Service propFromProp;


	public EntityImpl() throws Exception
	{
		propFromProp = Outside.service(this,"gus06.file.read.properties");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return propFromProp.t(obj);
	}
}
