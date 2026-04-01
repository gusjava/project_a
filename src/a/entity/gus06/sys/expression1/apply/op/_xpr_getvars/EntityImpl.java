package a.entity.gus06.sys.expression1.apply.op._xpr_getvars;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160809";}

	private Service getVars;
	
	public EntityImpl() throws Exception
	{
		getVars = Outside.service(this,"gus06.sys.parser3.tool.getvars");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return getVars.t(obj);
		if(obj instanceof List) return getVars.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
