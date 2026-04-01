package a.entity.gus06.java.compiler.outputanalyzer;

import a.framework.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140726";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now() {return sdf.format(new Date());}


	private Service errorHolder;

	private PrintStream out0;
	private PrintStream out1;
	
	private StringBuffer b;
	private Map info;



	public EntityImpl() throws Exception
	{
		errorHolder = Outside.service(this,"gus06.java.compiler.errorholder");
		out0 = (PrintStream) Outside.resource(this,"sysout");
		out1 = new PrintStream1();
	}


	public Object g() throws Exception
	{return out1;}


	private class PrintStream1 extends PrintStream
	{
		public PrintStream1()
		{super(new OutputStreamNull());}

		public void println(String line)
		{
			out0.println(line);
			handleLine(line);
		}
	}

	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){}
		public void write(int b){}
	}





	private void handleLine(String line)
	{
		try
		{
			if(line.startsWith("Note:")) return;

			if(line.contains("compilation failed"))
			{
				if(info==null) throw new Exception("Info map not initialized before line: "+line);
				updateState();
				return;
			}

			if(line.contains("compilation successful"))
			{
				info=null;
				errorHolder.e();
				return;
			}

			Map info1 = getNewInfo(line);

			if(info1!=null)
			{
				updateState();
				info = info1;
			}
			else if(info!=null)
			{
				if(!info.containsKey("line")) {info.put("line",line);return;}
				if(!info.containsKey("pos")) {info.put("pos",""+line.indexOf("^"));return;}
			}
		}
		catch(Exception e)
		{Outside.err(this,"handleLine(String)",e);}
	}

				
				
	private void updateState() throws Exception
	{
		if(info!=null)
		{
			info.put("text",toString(b));
			errorHolder.p(info);
			info = null;
			b = null;
		}
		else errorHolder.e();
	}


	private Map getNewInfo(String line) throws Exception
	{
		if(!line.contains(".java:")) return null;

		String[] n = line.split("\\.java:");
		File file = new File(n[0]+".java");
		if(!file.exists()) return null;

		String[] m = n[1].split(":");
		if(m.length<3) throw new Exception("Invalid line: "+line);
		
		String lineNb = m[0];
		String desc = m[2];

		if(desc.startsWith(" "))
		desc = desc.substring(1);

		b = new StringBuffer();
		b.append(line+"\n");

		Map info = new HashMap();
		info.put("file",file);
		info.put("lineNb",lineNb);
		info.put("description",desc);
		info.put("type",type(desc));
		info.put("time",now());
		return info;
	}
		
		
	private String type(String desc)
	{
		if(desc.equals("cannot find symbol")) return "1";
		return "?";
	}
		
	private String toString(Object obj)
	{return obj!=null?obj.toString():"";}
}