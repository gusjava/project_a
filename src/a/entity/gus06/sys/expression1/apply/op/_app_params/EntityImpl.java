package a.entity.gus06.sys.expression1.apply.op._app_params;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}

	public static final String T = "constant";
	

	private Map params;
		
	public EntityImpl() throws Exception
	{
		params = (Map) Outside.resource(this,"params");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return params;
	}
}
