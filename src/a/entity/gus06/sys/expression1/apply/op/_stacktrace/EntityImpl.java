package a.entity.gus06.sys.expression1.apply.op._stacktrace;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Exception) return ((Exception) obj).getStackTrace();
		if(obj instanceof Thread) return ((Thread) obj).getStackTrace();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
