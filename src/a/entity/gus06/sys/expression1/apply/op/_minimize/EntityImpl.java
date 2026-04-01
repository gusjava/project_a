package a.entity.gus06.sys.expression1.apply.op._minimize;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}


	private Service trans;
	
	public EntityImpl() throws Exception
	{trans = Outside.service(this,"gus06.string.transform.character.remove.whitespace");}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return trans.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
