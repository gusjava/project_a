package a.entity.gus06.sys.expression1.apply.op._default_zero;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220604";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return 0.0;
		if(obj.equals("")) return 0.0;
		return obj;
	}
}