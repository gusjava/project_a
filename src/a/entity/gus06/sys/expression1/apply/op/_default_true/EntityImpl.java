package a.entity.gus06.sys.expression1.apply.op._default_true;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160405";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.TRUE;
		if(obj.equals("")) return Boolean.TRUE;
		return obj;
	}
}