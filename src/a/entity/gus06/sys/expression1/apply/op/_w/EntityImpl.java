package a.entity.gus06.sys.expression1.apply.op._w;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160909";}


	private Service wrap;
	
	public EntityImpl() throws Exception
	{
		wrap = Outside.service(this,"gus06.reflection.wrap.object");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		return wrap.t(obj);
	}
}
