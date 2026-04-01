package a.entity.gus06.sys.expression1.apply.op._app_entities;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}

	public static final String T = "constant";
	

	private Map map;
		
	public EntityImpl() throws Exception
	{
		map = (Map) Outside.resource(this,"entitymap");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return map;
	}
}
