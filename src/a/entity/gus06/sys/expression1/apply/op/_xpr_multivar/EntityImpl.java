package a.entity.gus06.sys.expression1.apply.op._xpr_multivar;

import a.framework.*;
import java.util.List;
import java.util.Set;

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
		if(obj instanceof String) return check(obj);
		if(obj instanceof List) return check(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Boolean check(Object exp) throws Exception
	{
		Set vars = (Set) getVars.t(exp);
		return Boolean.valueOf(vars.size()>1);
	}
}
