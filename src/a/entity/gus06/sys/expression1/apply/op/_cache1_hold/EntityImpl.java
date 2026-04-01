package a.entity.gus06.sys.expression1.apply.op._cache1_hold;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180307";}


	private Service buildHolder;
	
	public EntityImpl() throws Exception
	{
		buildHolder = Outside.service(this,"gus06.sys.cache1.holder");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof String) return buildHolder.t((String) obj);
		if(obj instanceof Integer) return buildHolder.t(""+obj);
		if(obj instanceof Long) return buildHolder.t(""+obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
