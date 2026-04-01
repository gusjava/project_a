package a.entity.gus06.io.printstream.delayed1;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161218";}
	
	public static final long DEFAULT_LAPSE = 30;


	public Object t(Object obj) throws Exception
	{
		if(obj instanceof PrintStream)
			return new PrintStream1((PrintStream) obj,DEFAULT_LAPSE);
			
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
			PrintStream p = (PrintStream) o[0];
			long lapse = toLong(o[1]);
			
			if(lapse==-1) return p;
			return new PrintStream1(p,lapse);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}



	
	private static final OutputStreamNull nullOutput = new OutputStreamNull();
	
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
	
	
	public class PrintStream1 extends PrintStream implements Runnable, V
	{
		private PrintStream p;
		private StringBuffer b;
		
		private volatile boolean closed;
		private volatile long lapse;
		
		
		public PrintStream1(PrintStream p, long lapse)
		{
			super(nullOutput);
			this.p = p;
			this.lapse = lapse;
			
			b = new StringBuffer();
			closed = false;
			new Thread(this,"THREAD_"+getClass().getName()).start();
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("lapse")) {lapse = toLong(obj);return;}
			throw new Exception("Unknown key: "+key);
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
		
		public void print(char[] val)		{print(new String(val));}
		public void print(boolean val)		{print(""+val);}
		public void print(char val)		{print(""+val);}
		public void print(double val)		{print(""+val);}
		public void print(float val)		{print(""+val);}
		public void print(int val)		{print(""+val);}
		public void print(long val)		{print(""+val);}
		public void print(Object val)		{print(""+val);}
		public void println(String m)		{print(m+"\n");}
		
		
		public void print(String m)
		{if(!closed) b.append(m);}
		
		
		public void close()
		{closed = true;}
		
		public void flush()
		{p.flush();}
		
		
		public void run()
		{
			while(true)
			{
				sleep1();
				if(b.length()>0)
				{
					char c = b.charAt(0);
					b.deleteCharAt(0);
					p.print(c);
				}
			}
			
		}
		
		private void sleep1()
		{
			try{Thread.sleep(lapse);}
			catch(Exception e){}
		}
	}
}
