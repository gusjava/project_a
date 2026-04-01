package a.entity.gus06.command.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import a.framework.*;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140703";}

	
	private PrintStream out;

	public EntityImpl() throws Exception
	{
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String command = (String) obj;
		
		Process p = Runtime.getRuntime().exec(command);
		
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String line = null;
		while((line = readLine(br)) != null)
			out.println(line);
		br.close();
	}
	
	
	
	
	private String readLine(BufferedReader br)
	{
		try{return br.readLine();}
		catch(IOException e)
		{return null;}
	}
}
