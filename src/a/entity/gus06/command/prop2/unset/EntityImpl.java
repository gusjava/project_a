package a.entity.gus06.command.prop2.unset;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140808";}

	private Service unset0;
	private Service unset1;
	
	public EntityImpl() throws Exception
	{
		unset0 = Outside.service(this,"gus06.command.appfile.unset");
		unset1 = Outside.service(this,"gus06.command.prop.unset");
	}
	
	
	public void p(Object obj) throws Exception
	{
		unset0.p(obj);
		unset1.p(obj);
	}
}
