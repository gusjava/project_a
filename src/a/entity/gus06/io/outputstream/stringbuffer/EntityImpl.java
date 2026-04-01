package a.entity.gus06.io.outputstream.stringbuffer;

import a.framework.*;
import java.io.OutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190430";}
	
	
	public Object t(Object obj) throws Exception
	{return new OutputStreamStringBuffer((StringBuffer) obj);}
	
	
	private class OutputStreamStringBuffer extends OutputStream
	{
		private StringBuffer sb;
		private ByteArrayOutputStream1 bos;
		
		public OutputStreamStringBuffer(StringBuffer sb)
		{
			this.sb = sb;
			bos = new ByteArrayOutputStream1();
		}
		
		public void write(int b) throws IOException
		{
			bos.write(b);
			if(bos.hasValid())
			{
				String s = bos.toString("UTF-8");
				sb.append(s);
				bos.reset();
			}
		}
	}
	
	
	
	private class ByteArrayOutputStream1 extends ByteArrayOutputStream
	{
		public boolean hasValid()
		{return count>0;}
	}
}
