package a.entity.gus.z.server1.engine;

import java.net.ServerSocket;
import java.net.Socket;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G, Runnable {
	public String creationDate() {return "20260405";}

	private static final int PORT = 4000;


	private Service build;
	private Service wrap;
	
	private ServerSocket serverSocket;
	private String message;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus.x.socket.server.build");
		wrap = Outside.service(this,"gus.x.socket.server.wrap1");
		
		serverSocket = new ServerSocket(PORT);

		Thread t = new Thread(this, "THREAD_" + getClass().getName());
		t.setDaemon(true);
		t.start();
	}

	public void run() {
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				handleSocket(socket);
			}
		}
		catch (Exception e) {
			Outside.err(this, "run()", e);
		}
	}

	public void p(Object obj) throws Exception {
		
	}

	public Object g() throws Exception
	{return message;}

	private void handleSocket(Socket socket) {
	}

	private void handleMessage(String message)
	{
		this.message = message;
		received();
	}
	
	private void received()
	{send(this, "received()");}
}