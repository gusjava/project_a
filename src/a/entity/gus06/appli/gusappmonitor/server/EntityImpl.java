package a.entity.gus06.appli.gusappmonitor.server;

import a.framework.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EntityImpl extends S1 implements Entity, Runnable {

	public String creationDate() {return "20190311";}
	
	public static final int PORT = 4567;


	private Service buildServer;
	private Service consoleGui;
	private Service handleSocket;
	
	private ServerSocket serverSocket;
	private Thread t;


	public EntityImpl() throws Exception
	{
		buildServer = Outside.service(this,"gus06.socket.server.build");
		consoleGui = Outside.service(this,"gus06.appli.gusappmonitor.gui.console");
		handleSocket = Outside.service(this,"gus06.appli.gusappmonitor.handlesocket");
		
		serverSocket = (ServerSocket) buildServer.t(PORT);
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{while(true) perform();}
	
	
	private void perform()
	{
		try
		{
			println("waiting for connection...");
			Socket socket = serverSocket.accept();
			handleSocket.p(socket);
			println("new socket accepted: "+socket);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	private void println(String line) throws Exception
	{consoleGui.p(line);}
}
