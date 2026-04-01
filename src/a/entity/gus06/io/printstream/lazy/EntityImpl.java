package a.entity.gus06.io.printstream.lazy;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151024";}


	public Object t(Object obj) throws Exception
	{return new PrintStream1((File) obj);}
	
	
		
		
		
	private PrintStream build(File file)
	{
		try
		{
			File dir = file.getParentFile();
			if(!dir.exists()) dir.mkdirs();
			if(!file.exists()) file.createNewFile();
			return new PrintStream(file);
		}
		catch(IOException e)
		{Outside.err(this,"build(File)",e);} 
		return null;
	}



	
	private static final OutputStreamNull nullOutput = new OutputStreamNull();
	
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
	
	
	public class PrintStream1 extends PrintStream
	{
		private File file;
		private PrintStream p;
		
		public PrintStream1(File file)
		{
			super(nullOutput);
			this.file = file;
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
		
		public void print(String m)
		{
			if(p==null) p = build(file);
			if(p!=null) p.print(m);
		}
		
		public void println(String m)
		{
			if(p==null) p = build(file);
			if(p!=null) p.println(m);
		}
		
		public void close()
		{if(p!=null) p.close();}
		
		public void flush()
		{if(p!=null) p.flush();}
	}
}
