package a.entity.gus06.sys.expression1.apply.op._ueh;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191219";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof Thread) return ((Thread) obj).getUncaughtExceptionHandler();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
