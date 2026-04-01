package a.entity.gus06.app.prop.submap.autocomplete;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140919";}

	// USED BY: gus.swing.textcomp.cust.action.f1.autocomplete.perform
	public static final String ID = "autocomplete";

	private Service subMap;

	public EntityImpl() throws Exception
	{subMap = Outside.service(this,"gus06.app.prop.submap");}
	
	public Object g() throws Exception
	{return subMap.r(ID);}
}
