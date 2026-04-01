package a.entity.gus06.sys.expression1.apply.op._i;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160530";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof I) return ((I) obj).i();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
