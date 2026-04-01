package a.entity.gus06.io.printstream.sum;

import a.framework.*;
import java.io.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160310";}
	
	

	public Object t(Object obj) throws Exception
	{
		PrintStream[] pp = (PrintStream[]) obj;
		return new PrintStream1(pp);
	}
	
	
	
	
	private class PrintStream1 extends PrintStream
	{
		private PrintStream[] pp;
		
		public PrintStream1(PrintStream[] pp)
		{
			super(new OutputStreamNull());
			this.pp = pp;
		}
	
		public void println()			{for(PrintStream p:pp) p.println();}
		public void println(char[] val)		{for(PrintStream p:pp) p.println(val);}
		public void println(boolean val)	{for(PrintStream p:pp) p.println(val);}
		public void println(char val) 		{for(PrintStream p:pp) p.println(val);}
		public void println(double val) 	{for(PrintStream p:pp) p.println(val);}
		public void println(float val)    	{for(PrintStream p:pp) p.println(val);}
		public void println(int val)  		{for(PrintStream p:pp) p.println(val);}
		public void println(long val) 		{for(PrintStream p:pp) p.println(val);}
		public void println(Object val)   	{for(PrintStream p:pp) p.println(val);}
		public void println(String val)		{for(PrintStream p:pp) p.println(val);}
	    
		public void print(char[] val)    	{for(PrintStream p:pp) p.print(val);}
		public void print(boolean val)    	{for(PrintStream p:pp) p.print(val);}
		public void print(char val)     	{for(PrintStream p:pp) p.print(val);}
		public void print(double val)      	{for(PrintStream p:pp) p.print(val);}
		public void print(float val)      	{for(PrintStream p:pp) p.print(val);}
		public void print(int val)      	{for(PrintStream p:pp) p.print(val);}
		public void print(long val)     	{for(PrintStream p:pp) p.print(val);}
		public void print(Object val)    	{for(PrintStream p:pp) p.print(val);}
		public void print(String val)    	{for(PrintStream p:pp) p.print(val);}
	}
		
		
		
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
}
