package a.entity.gus06.convert.stringbuffertoprintstream;

import a.framework.*;
import java.io.PrintStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}

	
	public Object t(Object obj) throws Exception
	{return new PrintStream1((StringBuffer) obj);}
	
	
	private class PrintStream1 extends PrintStream
	{
		private StringBuffer b;
		
		public PrintStream1(StringBuffer b)
		{
			super(new OutputStreamNull());
			this.b = b;
		}
		
		public void println()			{b.append("\n");}
		public void println(char[] val)		{b.append(val+"\n");}
		public void println(boolean val)	{b.append(val+"\n");}
		public void println(char val) 		{b.append(val+"\n");}
		public void println(double val) 	{b.append(val+"\n");}
		public void println(float val)    	{b.append(val+"\n");}
		public void println(int val)  		{b.append(val+"\n");}
		public void println(long val) 		{b.append(val+"\n");}
		public void println(Object val)   	{b.append(val+"\n");}
		public void println(String val)		{b.append(val+"\n");}
	    
		public void print(char[] val)    	{b.append(val);}
		public void print(boolean val)    	{b.append(val);}
		public void print(char val)     	{b.append(val);}
		public void print(double val)      	{b.append(val);}
		public void print(float val)      	{b.append(val);}
		public void print(int val)      	{b.append(val);}
		public void print(long val)     	{b.append(val);}
		public void print(Object val)    	{b.append(val);}
		public void print(String val)    	{b.append(val);}
	}
	
	
	
	
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
}
