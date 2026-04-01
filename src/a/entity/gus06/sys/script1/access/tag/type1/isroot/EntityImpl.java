package a.entity.gus06.sys.script1.access.tag.type1.isroot;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160326";}
	
	public static final String ROOT = "root";


	private Service getType;


	public EntityImpl() throws Exception
	{
		getType = Outside.service(this,"gus06.sys.script1.access.tag.type1");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String type = (String) getType.t(obj);
		return type.equals(ROOT);
	}
}
