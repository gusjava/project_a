package a.entity.gus06.io.printstream.formatter.csv2;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150702";}

	public static String STRING_DELIM = ",";

	
	public Object t(Object obj) throws Exception
	{return new PrintStream1(toPrintStream(obj));}

	
	
	private PrintStream toPrintStream(Object obj) throws Exception
	{
		if(obj instanceof PrintStream) return (PrintStream) obj;
		if(obj instanceof OutputStream) return new PrintStream((OutputStream)obj);
		if(obj instanceof File) return new PrintStream((File)obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class PrintStream1 extends PrintStream
	{
		private PrintStream p;
		private boolean lineStarted = false;
		
		public PrintStream1(PrintStream p)
		{
			super(new OutputStreamNull());
			this.p = p;
		}
		
		public void print(String m)
		{
			if(lineStarted) p.print(STRING_DELIM);
			p.print(formatValue(m));
			lineStarted = true;
		}
		
		public void println(String m)
		{
			if(lineStarted) p.print(STRING_DELIM);
			p.print(formatValue(m));
			p.println();
			lineStarted = false;
		}
		
		public void println(Object obj)
		{
			String[] row = (String[]) obj;
			for(int i=0;i<row.length;i++)
			{
				if(i>0) p.print(STRING_DELIM);
				p.print(formatValue(row[i]));
			}
			p.println();
			lineStarted = false;
		}
		
		public void println()
		{
			p.println();
			lineStarted = false;
		}
		
		public void close()
		{
			p.close();
		}
	}

	
	
	
	public String formatValue(String value)
	{
		if(value.contains("\"") || value.contains("\n") || value.contains("\r") || value.contains(STRING_DELIM))
		{
			value = value.replace("\"","\"\"");
			return "\""+value+"\"";
		}
		return value;
	}
	
	
	
	private class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
}
