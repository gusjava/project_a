package a.entity.gus06.sys.expression1.apply.op._e_initsyserr;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160310";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof PrintStream) return new E1((PrintStream) value);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private PrintStream p;
		
		public E1(PrintStream p)
		{this.p = p;}
		
		public void e() throws Exception
		{System.setErr(p);}
	}
}
