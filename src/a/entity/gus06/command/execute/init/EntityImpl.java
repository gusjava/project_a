package a.entity.gus06.command.execute.init;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20140701";}

	
	private Map params;
	private PrintStream out;
	private Service execute;
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now() {return sdf.format(new Date());}
	
	
	
	
	public EntityImpl() throws Exception
	{
		params = (Map) Outside.resource(this,"params");
		out = (PrintStream) Outside.resource(this,"sysout");
		execute = Outside.service(this,"gus06.command.execute");


		String cmd = param("cmd");
		if(cmd!=null)
		{
			out.println("Executing command: "+cmd);
			execute.p(cmd);
			return;
		}

		Scanner sc = new Scanner(System.in);
		out.println("Session started");
		out.println("time: "+now());
		out.println("___________");

		String line = null;
		while(!(line = nextLine(sc)).equals("exit"))
		{
			//out.println(line);
			execute.p(line);
			out.println("___________");
		}
		
		out.println("Session closed");
	}
	
	
	
	
	private String param(String key)
	{
		if(!params.containsKey("cmd")) return null;
		String cmd = (String) params.get("cmd");
		return cmd.equals("")?null:cmd;
	}
	
	
	private String nextLine(Scanner sc)
	{
		out.print(">");
		return sc.nextLine();
	}
}
