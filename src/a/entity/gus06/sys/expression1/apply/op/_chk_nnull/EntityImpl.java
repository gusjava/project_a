package a.entity.gus06.sys.expression1.apply.op._chk_nnull;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180424";}
	
	
	
	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) throw new Exception("Object is expected to be not null: "+obj);
		return obj;
	}
}
