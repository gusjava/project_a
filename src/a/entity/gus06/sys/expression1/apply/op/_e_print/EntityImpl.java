package a.entity.gus06.sys.expression1.apply.op._e_print;

import a.framework.*;
import java.io.PrintStream;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151204";}
	
	
	private Service getContext;
	private Service getOutput;

	public EntityImpl() throws Exception
	{
		getContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
		getOutput = Outside.service(this,"gus06.sys.script1.access.context.output0");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof PrintStream)
			return new T1((PrintStream) value);
		return new E2(output(opMap),""+value);
	}
	
	
	private class T1 implements T
	{
		private PrintStream p;
		public T1(PrintStream p) {this.p = p;}
		
		public Object t(Object obj) throws Exception
		{return new E1(p,(String) obj);}
	}
	
	
	private class E1 implements E
	{
		private PrintStream p;
		private String s;
		
		public E1(PrintStream p, String s)
		{
			this.p = p;
			this.s = s;
		}
		public void e() throws Exception
		{p.print(s);}
	}
	
	
	private class E2 implements E
	{
		private P p;
		private String s;
		
		public E2(P p, String s)
		{
			this.p = p;
			this.s = s;
		}
		public void e() throws Exception
		{p.p(s+"\n");}
	}
	
	
	private P output(Map opMap) throws Exception
	{
		Map context = (Map) getContext.t(opMap);
		return (P) getOutput.t(context);
	}
}
