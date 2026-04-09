package a.entity.gus.y.appli1.en.execute.restart;

import a.framework.*;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20260409";}
	
	private Service findArgs;
	private Service restart;

	public EntityImpl() throws Exception
	{
		findArgs = Outside.service(this,"gus06.app.argsline");
		restart = Outside.service(this,"gus06.app.restart");
	}
	
	public void e() throws Exception
	{
		String args = (String) findArgs.g();
		restart.p(args);
	}
}
