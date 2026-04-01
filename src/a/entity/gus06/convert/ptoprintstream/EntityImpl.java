package a.entity.gus06.convert.ptoprintstream;

import a.framework.*;
import java.io.PrintStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161109";}

	
	public Object t(Object obj) throws Exception
	{return new PrintStream1((P) obj);}
	
	
	private class PrintStream1 extends PrintStream
	{
		private P p;
		
		public PrintStream1(P p)
		{
			super(nullOutput);
			this.p = p;
		}
		
		public void println()			{println("");}
		public void println(char[] val)		{println(new String(val));}
		public void println(boolean val)	{println(""+val);}
		public void println(char val)		{println(""+val);}
		public void println(double val)		{println(""+val);}
		public void println(float val)		{println(""+val);}
		public void println(int val)		{println(""+val);}
		public void println(long val)		{println(""+val);}
		public void println(Object val)		{println(""+val);}
		public void println(String m)		{print(m+"\n");}
		
		public void print(char[] val)		{print(new String(val));}
		public void print(boolean val)		{print(""+val);}
		public void print(char val)		{print(""+val);}
		public void print(double val)		{print(""+val);}
		public void print(float val)		{print(""+val);}
		public void print(int val)		{print(""+val);}
		public void print(long val)		{print(""+val);}
		public void print(Object val)		{print(""+val);}
		
		public void print(String m)
		{handlePrint(p,m);}
	}
	
	
	private void handlePrint(P p, String m)
	{
		try
		{
			p.p(m);
		}
		catch(Exception e)
		{Outside.err(this,"handlePrint(P,String)",e);}
	}

	
	
	private static final OutputStreamNull nullOutput = new OutputStreamNull();
	
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
}
