package a.entity.gus06.app.restart0;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150626";}

	
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
