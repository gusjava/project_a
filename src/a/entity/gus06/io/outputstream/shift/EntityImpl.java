package a.entity.gus06.io.outputstream.shift;

import a.framework.*;
import java.io.OutputStream;
import java.io.IOException;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20190430";}
	
	
	public Object t(Object obj) throws Exception
	{return new OutputStreamShift((OutputStream) obj);}
	
	public Object g() throws Exception
	{return new OutputStreamShift(null);}
	
	
	
	private class OutputStreamShift extends OutputStream implements P, G
	{
		private OutputStream os;
		public OutputStreamShift(OutputStream os)
		{this.os = os;}
		
		public void write(int b) throws IOException
		{if(os!=null) os.write(b);}
		
		public void p(Object obj) throws Exception
		{os = (OutputStream) obj;}
		
		public Object g() throws Exception
		{return os;}
	}
}
