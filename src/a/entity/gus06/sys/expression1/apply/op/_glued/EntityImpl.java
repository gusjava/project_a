package a.entity.gus06.sys.expression1.apply.op._glued;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160203";}


	private Service glued;
	
	public EntityImpl() throws Exception
	{
		glued = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower.glued");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return glued.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
