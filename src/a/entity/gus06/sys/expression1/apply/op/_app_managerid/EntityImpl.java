package a.entity.gus06.sys.expression1.apply.op._app_managerid;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170411";}

	public static final String T = "constant";
	

	private String managerId;
		
	public EntityImpl() throws Exception
	{
		managerId = (String) Outside.resource(this,"core.id");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return managerId;
	}
}
