package a.entity.gus06.sys.textbuilder1.parser.findformats;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160302";}
	
	public static final String START = "format.";


	private Service subMap;


	public EntityImpl() throws Exception
	{
		subMap = Outside.service(this,"gus06.map.string.submap");
	}
	
	public Object t(Object obj) throws Exception
	{
		return subMap.t(new Object[]{obj,START});
	}
}
