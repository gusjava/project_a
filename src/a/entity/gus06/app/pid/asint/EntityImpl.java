package a.entity.gus06.app.pid.asint;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170410";}


	private Service find;


	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.app.pid");
	}
	
	public Object g() throws Exception
	{
		String pid = (String) find.g();
		return Integer.valueOf(pid);
	}
}