package a.entity.gus06.command.entity.compile;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140725";}


	private Service compiler;
	private Service checkValid;
	
	
	public EntityImpl() throws Exception
	{
		compiler = Outside.service(this,"gus06.java.compiler.entity");
		checkValid = Outside.service(this,"gus06.entitydev.entityname.check.valid");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String name = (String) checkValid.t(obj);
		compiler.p(name);
	}
}
