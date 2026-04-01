package a.entity.gus06.app.jarfile.extract1.resources2;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140908";}


	private Service extract;
	private Service check;
	private Service clear;
	
	
	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.app.jarfile.extract1.resources");
		check = Outside.service(this,"gus06.app.jarfile.extract1.resources2.check");
		clear = Outside.service(this,"gus06.app.jarfile.extract1.resources2.clear");
	}
	
	
	public void e() throws Exception
	{
		if(!check.f(null)) return;
		
		clear.e();
		extract.e();
	}
}
