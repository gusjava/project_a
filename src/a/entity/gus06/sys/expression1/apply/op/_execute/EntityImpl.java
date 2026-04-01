package a.entity.gus06.sys.expression1.apply.op._execute;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof E) return execute((E) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object execute(E value)
	{
		try
		{
			long t1 = System.currentTimeMillis();
			value.e();
			long t2 = System.currentTimeMillis();
			
			return Long.valueOf(t2-t1);
		}
		catch(Exception e) {return e;}
	}
}
