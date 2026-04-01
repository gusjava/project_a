package a.entity.gus06.app.prop.submap.option;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140919";}

	// USED BY: gus.options.init
	public static final String ID = "option";

	private Service subMap;

	public EntityImpl() throws Exception
	{subMap = Outside.service(this,"gus06.app.prop.submap");}
	
	public Object g() throws Exception
	{return subMap.r(ID);}
}
