package a.entity.gus06.sys.expression1.apply.op._new_smap;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221105";}


	private Service build;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.map.build.supportmap");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof Map) return build.t(obj);
		if(obj instanceof String) return build.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}