package a.entity.gus06.sys.expression1.apply.op._app_main;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160412";}

	public static final String T = "constant";
	

	private Map main;
		
	public EntityImpl() throws Exception
	{
		main = (Map) Outside.resource(this,"main");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return main;
	}
}
