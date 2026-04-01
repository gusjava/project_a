package a.entity.gus06.sys.expression1.apply.op._norm_authorname;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220904";}


	private Service norm;
	
	public EntityImpl() throws Exception
	{
		norm = Outside.service(this,"gus06.string.transform.normalize.authorname");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return norm.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}