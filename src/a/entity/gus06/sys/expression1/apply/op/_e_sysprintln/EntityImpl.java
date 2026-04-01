package a.entity.gus06.sys.expression1.apply.op._e_sysprintln;

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
		return new E1(value);
	}
	
	
	
	private class E1 implements E
	{
		private Object value;
		public E1(Object value)
		{this.value = value;}
		
		public void e() throws Exception
		{System.out.println(value);}
	}
}
