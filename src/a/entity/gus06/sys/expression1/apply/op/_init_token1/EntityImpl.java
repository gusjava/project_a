package a.entity.gus06.sys.expression1.apply.op._init_token1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}


	private Service store;
	
	public EntityImpl() throws Exception
	{
		store = Outside.service(this,"gus06.sys.token1.store");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Map) return store.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
