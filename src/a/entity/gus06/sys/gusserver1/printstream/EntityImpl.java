package a.entity.gus06.sys.gusserver1.printstream;

import java.io.OutputStream;
import java.io.PrintStream;

import a.framework.*;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20140705";}

	private PrintStream redirect;
	private PrintStream sysout;
	private PrintStream serverOut;

	public EntityImpl() throws Exception
	{
		sysout = (PrintStream) Outside.resource(this,"sysout0");
		serverOut = new PrintStream1();
	}
	
	
	public Object g() throws Exception
	{return serverOut;}
	
	
	public void p(Object obj) throws Exception
	{redirect = (PrintStream) obj;}
	
	
	private PrintStream current()
	{return redirect!=null?redirect:sysout;}
	
	
	
	private class PrintStream1 extends PrintStream
	{
		public PrintStream1()
		{super(new OutputStreamNull());}
		
		public void println()			{current().println();}
		public void println(char[] val)		{current().println(val);}
		public void println(boolean val)	{current().println(val);}
		public void println(char val)		{current().println(val);}
		public void println(double val)		{current().println(val);}
		public void println(float val)		{current().println(val);}
		public void println(int val)		{current().println(val);}
		public void println(long val)		{current().println(val);}
		public void println(Object val)		{current().println(val);}
		public void println(String val)		{current().println(val);}
		
		public void print(char[] val)		{current().print(val);}
		public void print(boolean val)	  	{current().print(val);}
		public void print(char val)		{current().print(val);}
		public void print(double val)		{current().print(val);}
		public void print(float val)		{current().print(val);}
		public void print(int val)		{current().print(val);}
		public void print(long val)		{current().print(val);}
		public void print(Object val)		{current().print(val);}
		public void print(String val)		{current().print(val);}
		
		public void flush()			{current().flush();}
	}
	
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
}
