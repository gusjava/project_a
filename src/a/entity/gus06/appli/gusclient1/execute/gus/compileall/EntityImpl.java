package a.entity.gus06.appli.gusclient1.execute.gus.compileall;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140811";}

	private Service cmd;

	public EntityImpl() throws Exception
	{cmd = Outside.service(this,"gus06.command.compile");}
	
	public void e() throws Exception
	{cmd.e();}
}
