package a.entity.gus06.sys.expression1.apply.op._ref;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160918";}


	private Service manager;
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.cache1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof String) return manager.r((String) obj);
		if(obj instanceof Integer) return manager.r(""+obj);
		if(obj instanceof Long) return manager.r(""+obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}