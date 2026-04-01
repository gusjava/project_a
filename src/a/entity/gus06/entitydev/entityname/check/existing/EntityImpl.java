package a.entity.gus06.entitydev.entityname.check.existing;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140829";}


	private Service checkName;
	
	public EntityImpl() throws Exception
	{
		checkName = Outside.service(this,"gus06.app.entity.checkname");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		if(checkName.f(name)) return name;
		throw new Exception("Unknown entity name: "+name);
	}
}
