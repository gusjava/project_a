package a.entity.gus06.io.generate.pipedinput.withp;

import a.framework.*;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.io.PrintStream;
import java.io.IOException;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180403";}
	
	
	public Object g() throws Exception
	{
		PipedOutputStream pos = new PipedOutputStream();
		return new InputStream1(pos);
	}
	
	
	private class InputStream1 extends PipedInputStream implements P
	{
		private PrintStream p;
		
		public InputStream1(PipedOutputStream pos) throws IOException
		{
			super(pos);
			p = new PrintStream(pos);
		}
		
		public void p(Object obj) throws Exception
		{
			p.println(""+obj);
		}
	}
}
