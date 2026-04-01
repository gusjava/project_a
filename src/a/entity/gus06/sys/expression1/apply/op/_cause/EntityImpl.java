package a.entity.gus06.sys.expression1.apply.op._cause;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Exception) return ((Exception) obj).getCause();
		if(obj instanceof Throwable) return ((Throwable) obj).getCause();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
