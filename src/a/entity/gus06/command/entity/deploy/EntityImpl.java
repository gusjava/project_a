package a.entity.gus06.command.entity.deploy;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140730";}


	private Service deploy;
	private Service checkValid;

	public EntityImpl() throws Exception
	{
		deploy = Outside.service(this,"gus06.entitydev.deploy.buildjar");
		checkValid = Outside.service(this,"gus06.entitydev.entityname.check.valid");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String name = (String) checkValid.t(obj);
		deploy.p(name);
	}
}
