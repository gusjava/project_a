package a.entity.gus06.sys.expression1.apply.op._toint;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj.equals("")) return null;
		
		if(obj instanceof Number) return Integer.valueOf(((Number) obj).intValue());
		if(obj instanceof String) return Integer.valueOf((String) obj);
		if(obj instanceof Boolean) return Integer.valueOf(((Boolean) obj)?1:0);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}