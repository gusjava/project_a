package a.entity.gus06.sys.gusappmonitor.client.handlemessage;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190316";}
	
	public static final String COMMAND_KILL = "kill";
	public static final String COMMAND_EXIT = "exit";
	public static final String COMMAND_RESTART = "restart";
	public static final String COMMAND_STACKTRACE = "stacktrace";
	public static final String COMMAND_THREADSTATE = "threadstate";


	private Service executeKill;
	private Service executeExit;
	private Service executeRestart;
	private Service handleStacktrace;
	private Service handleThreadState;
	
	private PrintStream out;


	public EntityImpl() throws Exception
	{
		executeKill = Outside.service(this,"gus06.app.execute.kill");
		executeExit = Outside.service(this,"gus.y.app1.execute.exit");
		executeRestart = Outside.service(this,"gus06.app.restart");
		handleStacktrace = Outside.service(this,"gus06.sys.gusappmonitor.client.handle.stacktrace");
		handleThreadState = Outside.service(this,"gus06.sys.gusappmonitor.client.handle.threadstate");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		G holder = (G) obj;
		String message = (String) holder.g();
		
		out.println("GusAppMonitor : "+message);
		
		if(message.contains(":"))
		{
			String[] n = message.split(":",2);
			handle(holder,n[0],n[1]);
		}
		else handle(holder,message);
	}
	
	
	
	private void handle(G holder, String key) throws Exception
	{
		if(key.equals(COMMAND_KILL)) executeKill.e();
		else if(key.equals(COMMAND_EXIT)) executeExit.e();
		else if(key.equals(COMMAND_RESTART)) executeRestart.e();
		else if(key.equals(COMMAND_THREADSTATE)) handleThreadState.p(holder);
		else throw new Exception("Invalid key: "+key);
	}
	
	private void handle(G holder, String key, String info) throws Exception
	{
		if(key.equals(COMMAND_STACKTRACE)) handleStacktrace.p(new Object[]{holder,info});
		else throw new Exception("Invalid key: "+key);
	}
}
