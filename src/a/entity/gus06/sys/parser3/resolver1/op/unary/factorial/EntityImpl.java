package a.entity.gus06.sys.parser3.resolver1.op.unary.factorial;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231107";}



	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object value = t.t(cut.get(0));
		if(value==null) return null;
		
		if(value instanceof Integer) return factorial((Integer) value);
		if(value instanceof Long) return factorial((Integer) value);
		
		throw new Exception("Invalid value type for operator: "+value.getClass().getName());
	}
	
	
	private int factorial(int n)
	{
		int fac = 1;
		for(int i=1;i<=n;i++) fac *= i;
		return fac;
	}
	
	private long factorial(long n)
	{
		long fac = 1;
		for(int i=1;i<=n;i++) fac *= i;
		return fac;
	}
}