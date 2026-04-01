package a.entity.gus06.sys.expression1.apply.op._solve_polynomial;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160722";}


	private Service solve;
	
	public EntityImpl() throws Exception
	{
		solve = Outside.service(this,"gus06.math.function.solve.polynomial");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof int[]) return solve.t(obj);
		if(obj instanceof double[]) return solve.t(obj);
		if(obj instanceof List) return solve.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
