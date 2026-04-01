package a.entity.gus06.ling.find.lingstring.notfound;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140808";}

	private Service register;

	public EntityImpl() throws Exception
	{
		register = Outside.service(this,"gus06.ling.gui.lingdir.register");
	}
	
	public void p(Object obj) throws Exception
	{
		register.v((String) obj,"");
	}
}
