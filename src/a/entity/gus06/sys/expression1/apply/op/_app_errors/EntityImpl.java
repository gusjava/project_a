package a.entity.gus06.sys.expression1.apply.op._app_errors;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}

	public static final String T = "constant";
	

	private Object errors;
		
	public EntityImpl() throws Exception
	{
		errors = Outside.resource(this,"errlist");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return errors;
	}
}
