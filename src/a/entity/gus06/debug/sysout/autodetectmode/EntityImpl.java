package a.entity.gus06.debug.sysout.autodetectmode;

import a.framework.*;
import java.io.PrintStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20160315";}


	public void e() throws Exception
	{
		PrintStream out = System.out;
		System.setOut(new PrintStream1(out));
	}
	
	
	private static final OutputStreamNull nullOutput = new OutputStreamNull();
	
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
	
	
	public class PrintStream1 extends PrintStream
	{
		private PrintStream p;
		
		public PrintStream1(PrintStream p)
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
		{
			p.print(header()+"\t"+m);
		}
		
		public void close()			{p.close();}
		public void flush()			{p.flush();}
	}
	
	
	
	
	private String header()
	{
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return toString(ste[5]);
	}
	
	
	private String toString(StackTraceElement ste)
	{return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";}
}
