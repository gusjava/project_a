package a.entity.gus06.sys.expression1.apply.op._app_mapping;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160530";}

	public static final String T = "constant";
	

	private Map mapping;
		
	public EntityImpl() throws Exception
	{
		mapping = (Map) Outside.resource(this,"mapping");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return mapping;
	}
}
