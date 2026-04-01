package a.entity.gus06.command.compile;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, E, P {

	public String creationDate() {return "20140722";}

	private Service compiler;

	public EntityImpl() throws Exception
	{compiler = Outside.service(this,"gus06.java.compiler1");}
	
	
	public void e() throws Exception
	{compiler.e();}


	public void p(Object obj) throws Exception
	{compiler.v((String) obj,null);}
}
