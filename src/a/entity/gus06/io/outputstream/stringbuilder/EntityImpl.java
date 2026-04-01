package a.entity.gus06.io.outputstream.stringbuilder;

import a.framework.*;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190430";}
	
	
	public Object t(Object obj) throws Exception
	{return new OutputStreamStringBuffer((StringBuilder) obj);}
	
	
	private class OutputStreamStringBuffer extends OutputStream
	{
		private StringBuilder sb;
		
		public OutputStreamStringBuffer(StringBuilder sb)
		{this.sb = sb;}
		
		public void write(int b)
		{ sb.append((char) b);}
	}
}
