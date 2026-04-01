package a.entity.gus06.sys.expression1.apply.op._e_sysprint;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160220";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof String) return new E1((String) value);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class E1 implements E
	{
		private String s;
		
		public E1(String s)
		{
			this.s = s;
		}
		public void e() throws Exception
		{System.out.print(s);}
	}
}
