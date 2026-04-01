package a.entity.gus06.command.restart;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140705";}

	
	
	public void e() throws Exception
	{
		String command = (String) Outside.resource(this,"property#exec.restart");
		if(command==null) throw new Exception("Undefined property: exec.restart");
		
		Runtime.getRuntime().exec(command);
		System.exit(0);
	}
}
