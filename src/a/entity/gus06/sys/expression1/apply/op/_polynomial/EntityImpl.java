package a.entity.gus06.sys.expression1.apply.op._polynomial;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}


	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.math.function.build.polynomial");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof int[]) return build.t(obj);
		if(obj instanceof double[]) return build.t(obj);
		if(obj instanceof List) return build.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
