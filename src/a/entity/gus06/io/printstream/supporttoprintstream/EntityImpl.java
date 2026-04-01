package a.entity.gus06.io.printstream.supporttoprintstream;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140730";}

	

	public Object t(Object obj) throws Exception
	{return new PrintStream1((S1) obj);}
	
	
	
	
	private class PrintStream1 extends PrintStream
	{
		private S1 s;
		
		public PrintStream1(S1 s)
		{
			super(new OutputStreamNull());
			this.s = s;
		}
	
		public void println()			{printed();}
		public void println(char[] val)		{printed();}
		public void println(boolean val)		{printed();}
		public void println(char val) 		{printed();}
		public void println(double val) 		{printed();}
		public void println(float val)    		{printed();}
		public void println(int val)  		{printed();}
		public void println(long val) 		{printed();}
		public void println(Object val)   		{printed();}
		public void println(String val)		{printed();}
	    
		public void print(char[] val)    		{printed();}
		public void print(boolean val)    		{printed();}
		public void print(char val)     		{printed();}
		public void print(double val)      		{printed();}
		public void print(float val)      		{printed();}
		public void print(int val)      		{printed();}
		public void print(long val)     		{printed();}
		public void print(Object val)    		{printed();}
		public void print(String val)    		{printed();}
		
		private void printed()
		{s.send(this,"printed()");}
	}
		
		
		
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
}
