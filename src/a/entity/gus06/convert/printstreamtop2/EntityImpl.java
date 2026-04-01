package a.entity.gus06.convert.printstreamtop2;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201223";}

	
	public Object t(Object obj) throws Exception
	{
		return new P1((PrintStream) obj);
	}
	
	private class P1 implements P, E
	{
		private PrintStream p;
		public P1(PrintStream p) {this.p = p;}
		
		public void p(Object obj) throws Exception
		{p.println(obj);}
		
		public void e() throws Exception
		{p.close();}
	}
}