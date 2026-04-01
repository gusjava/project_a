package a.entity.gus06.sys.expression1.apply.op._tochararray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160730";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof char[]) return obj;
		if(obj instanceof String) return ((String) obj).toCharArray();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
