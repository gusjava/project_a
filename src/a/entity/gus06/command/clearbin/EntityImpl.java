package a.entity.gus06.command.clearbin;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20200304";}

	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.java.compiler1.bin.empty");}
	
	public void e() throws Exception
	{perform.e();}
}