package a.entity.gus06.command.entity.remove;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140716";}


	private Service removeSrc;
	private Service removeClass;
	private Service checkOwn;
	
	
	public EntityImpl() throws Exception
	{
		removeSrc = Outside.service(this,"gus06.entitydev.refactor.remove1");
		removeClass = Outside.service(this,"gus06.entitydev.refactor.bin.remove1");
		checkOwn = Outside.service(this,"gus06.entitydev.entityname.check.own");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String name = (String) checkOwn.t(obj);
		
		removeSrc.p(name);
		removeClass.p(name);
	}
}
