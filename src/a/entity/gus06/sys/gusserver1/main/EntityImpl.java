package a.entity.gus06.sys.gusserver1.main;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import a.framework.*;

public class EntityImpl implements Entity, Runnable {

	public String creationDate() {return "20140701";}

	
	private Service handleSocket;
	private Service printLastErr;
	
	private ServerSocket serverSocket;
	
	private String port;
	private PrintStream out;
	private Thread t;
	
	
	
	public EntityImpl() throws Exception
	{
		handleSocket = Outside.service(this,"gus06.sys.gusserver1.handlesocket");
		printLastErr = Outside.service(this,"gus06.command.print.lasterror");
		port = (String) Outside.resource(this,"property#server.port");
		out = (PrintStream) Outside.resource(this,"sysout");
		
		if(port==null) throw new Exception("Server port is undefined");
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		try
		{
			serverSocket = new ServerSocket(Integer.parseInt(port));
			out.println("Gus Server started on port: "+port);
			
			boolean running = true;
			while(running)
			{
				Socket socket = serverSocket.accept();
				running = handleSocket(socket);
			}
			serverSocket.close();
			out.println("Gus Server closed");
			printLastErr.e();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		System.exit(0);
	}
	
	
	
	
	private boolean handleSocket(Socket socket)
	{
		try{return handleSocket.f(socket);}
		catch(Exception e) {Outside.err(this,"handleSocket(Socket)",e);}
		return false;
	}
	
}
