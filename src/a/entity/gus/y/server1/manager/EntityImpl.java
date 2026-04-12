package a.entity.gus.y.server1.manager;

import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, E, V {
	public String creationDate() {return "20260405";}

	private static final int PORT = 44000;

	private Service buildThread;
	private Service buildSession;
	private Service buildServer;
	private Service wrapServer;
	private Service engine;
	
	private Object server;
	private Object session;
	
	private ActionListener serverListener;
	private ActionListener sessionListener;
	
	private P in;
	private P out;

	public EntityImpl() throws Exception
	{
		buildThread = Outside.service(this,"gus.x.thread.wrap1");
		buildSession = Outside.service(this,"gus.x.socket.wrap2");
		buildServer = Outside.service(this,"gus.x.socket.server.build");
		wrapServer = Outside.service(this,"gus.x.socket.server.wrap1");
		engine = Outside.service(this,"gus.y.server1.watchdog");

		serverListener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("socketAccepted()")) socketAccepted();
				else if(s.equals("exceptionCatched()")) exceptionCatched();
			}
		};
		
		sessionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("connectionClosed()")) connectionClosed();
				else if(s.equals("connectionStarted()")) connectionStarted();
				else if(s.equals("messageReceived()")) messageReceived();
			}
		};
	}
	
	public void e() throws Exception
	{
		if(server!=null) throw new Exception("Server already initialized");
		
		server = wrapServer.t(buildServer.t(PORT));
		((S)server).addActionListener(serverListener);
		
		Thread serverT = (Thread) buildThread.t(server);
		serverT.start();
		
		out.p("local server started on port "+PORT);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("out")) {out = (P) obj;return;}
		if(key.equals("in")) {in = (P) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	// SESSION EVENTS
	
	private void connectionClosed()
	{
		try
		{
			endSession();
			in.p("Session exited");
		}
		catch(Exception e)
		{Outside.err(this,"connectionClosed()",e);}
	}
	
	private void connectionStarted()
	{
		try
		{
			in.p("Session is running...");
		}
		catch(Exception e)
		{Outside.err(this,"connectionStarted()",e);}
	}
	
	private void messageReceived()
	{
		try
		{
			if(session==null) return;
			
			Object input = ((R)session).r("lastInput");
			in.p(input);
			
			Object output = engine.t(input);
			out.p(output);
			
			((P)session).p(output);
		}
		catch(Exception e)
		{Outside.err(this,"messageReceived()",e);}
	}
	
	private void socketAccepted()
	{
		try
		{
			if(session!=null) throw new Exception("Session already created");
			
			Socket socket = (Socket) ((G) server).g();
			startSession(socket);
			
			String ip = socket.getRemoteSocketAddress().toString();
			in.p("Session started by "+ip);
		}
		catch(Exception e)
		{Outside.err(this,"socketAccepted()",e);}
	}

	private void exceptionCatched()
	{
		try
		{
			Exception exception = (Exception) ((R) server).r("exception");
			in.p("Exception occurred: "+exception);
		}
		catch(Exception e)
		{Outside.err(this,"exceptionCatched()",e);}
	}
	
	private void startSession(Socket socket) throws Exception
	{
		if(session!=null) throw new Exception("Session already started");
		session = buildSession.t(socket);
		((S) session).addActionListener(sessionListener);
		
		Thread sessionT = (Thread) buildThread.t(session);
		sessionT.start();
	}
	
	private void endSession() throws Exception
	{
		if(session==null) throw new Exception("Session not found");
		((S)session).removeActionListener(sessionListener);
		((P)session).p("exit");
		session = null;
	}
	
	private void received()
	{send(this, "received()");}
}
