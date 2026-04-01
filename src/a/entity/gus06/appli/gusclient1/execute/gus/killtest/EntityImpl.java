package a.entity.gus06.appli.gusclient1.execute.gus.killtest;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140811";}

	private Service cmd;

	public EntityImpl() throws Exception
	{cmd = Outside.service(this,"gus06.app.jarfile.o.process");}
	
	public void e() throws Exception
	{cmd.e();}
}
