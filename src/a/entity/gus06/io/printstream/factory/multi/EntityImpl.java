package a.entity.gus06.io.printstream.factory.multi;

import a.framework.*;
import java.io.*;
import java.util.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140730";}
	
	

	public Object g() throws Exception
	{return new PrintStream1();}
	
	
	
	
	private class PrintStream1 extends PrintStream implements P
	{
		private List<PrintStream> l = new ArrayList<>();
		
		public PrintStream1()
		{super(new OutputStreamNull());}
	
		public void p(Object obj)throws Exception
		{
			PrintStream p = (PrintStream) obj; 
			l.add(p);
		}
	
		public void println()			{for(PrintStream p:l) p.println();}
		public void println(char[] val)		{for(PrintStream p:l) p.println(val);}
		public void println(boolean val)	{for(PrintStream p:l) p.println(val);}
		public void println(char val) 		{for(PrintStream p:l) p.println(val);}
		public void println(double val) 	{for(PrintStream p:l) p.println(val);}
		public void println(float val)    	{for(PrintStream p:l) p.println(val);}
		public void println(int val)  		{for(PrintStream p:l) p.println(val);}
		public void println(long val) 		{for(PrintStream p:l) p.println(val);}
		public void println(Object val)   	{for(PrintStream p:l) p.println(val);}
		public void println(String val)		{for(PrintStream p:l) p.println(val);}
	    
		public void print(char[] val)    	{for(PrintStream p:l) p.print(val);}
		public void print(boolean val)    	{for(PrintStream p:l) p.print(val);}
		public void print(char val)     	{for(PrintStream p:l) p.print(val);}
		public void print(double val)      	{for(PrintStream p:l) p.print(val);}
		public void print(float val)      	{for(PrintStream p:l) p.print(val);}
		public void print(int val)      	{for(PrintStream p:l) p.print(val);}
		public void print(long val)     	{for(PrintStream p:l) p.print(val);}
		public void print(Object val)    	{for(PrintStream p:l) p.print(val);}
		public void print(String val)    	{for(PrintStream p:l) p.print(val);}
	}
		
		
		
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
}
