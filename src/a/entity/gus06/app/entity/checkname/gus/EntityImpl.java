package a.entity.gus06.app.entity.checkname.gus;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140828";}


	private Service check;


	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.app.entity.checkname");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String name = (String) obj;
		if(!check.f(name)) return false;
		
		return name.startsWith("gus.");
	}
}
