package a.entity.gus06.sys.socket1.engine;

import a.framework.*;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JScrollPane;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Map;

public class EntityImpl implements Entity, P, F, V {

	public String creationDate() {return "20250328";}
	
	public static final String KEY_ROOT = "path.root";
	public static final String KEY_LOCAL_PORT = "local.port";
	public static final String KEY_REMOTE_PORT = "remote.port";
	public static final String KEY_REMOTE_IP = "remote.ip";


	private Service buildServer;
	private Service buildSession;
	private Service buildThread;
	private Service handleExecute;
	private Service buildCcPrintStream;
	
	private PrintStream in;
	private PrintStream out;
	
	private Object server;
	private Object session;
	
	private ActionListener serverListener;
	private ActionListener sessionListener;
	
	private Thread t;
	private Object data;
	private String command;
	private PrintStream ccP;
	
	private File root;
	private Integer localPort;
	private String localIp;
	private Integer remotePort;
	private String remoteIp;
	

	public EntityImpl() throws Exception
	{
		buildServer = Outside.service(this,"gus06.socket.server.build1");
		buildSession = Outside.service(this,"gus06.sys.socket1.build.session");
		buildThread = Outside.service(this,"gus06.thread.manager");
		handleExecute = Outside.service(this,"*gus06.sys.socket1.handle.execute");
		buildCcPrintStream = Outside.service(this,"gus06.sys.socket1.build.ccprintstream");
		
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
		
		ccP = (PrintStream) buildCcPrintStream.g();
		P redirect = o -> {
			System.out.println("redirecting "+o);
			_sendCommand((String) o);
		};
		((V)ccP).v("offset","!");
		((V)ccP).v("redirect",redirect);
		
		handleExecute.v("out",ccP);
		handleExecute.v("out",System.out);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		root = (File) map.get(KEY_ROOT);
		localPort = (Integer) map.get(KEY_LOCAL_PORT);
		localIp = InetAddress.getLocalHost().getHostAddress();
		remotePort = (Integer) map.get(KEY_REMOTE_PORT);
		remoteIp = (String) map.get(KEY_REMOTE_IP);
		
		if(server!=null) ((S)server).removeActionListener(serverListener);
		server = buildServer.t(localPort);
		((S)server).addActionListener(serverListener);
		
		out.println("root directory: "+root);
		out.println("local server: "+localIp+":"+localPort);
		out.println("remote access: "+remoteIp+":"+remotePort);
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("out")) {initOut((PrintStream) obj);return;}
		if(key.equals("in")) {initIn((PrintStream) obj);return;}
		if(key.equals("sendData")) {sendData(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void initOut(PrintStream out) throws Exception
	{this.out = out;}
	
	private void initIn(PrintStream in) throws Exception
	{this.in = in;}
	
	
	
	public boolean f(Object obj) throws Exception
	{return sendCommand((String) obj);}
	

	
	// DATA
	
	private void sendData(Object data) throws Exception
	{
		if(data==null) throw new Exception("Invalid null data");
		
		if(isRunning()) return;
		this.data = data;
		sendCommand("send");
	}
	
	
	
	// THREAD
	
	private boolean isRunning()
	{return t!=null && t.isAlive();}
	
	
	private void start(Runnable r) throws Exception
	{
		if(isRunning()) return;
		t = (Thread) buildThread.t(r);
		t.start();
	}
	
	
	
	// COMMAND
	
	private boolean sendCommand(String cmd)
	{
		try
		{
			if(cmd==null) throw new Exception("Invalid null command");
			if(cmd.equals("")) throw new Exception("Invalid empty command");
			
			if(isRunning()) return false;
			command = cmd;
			start(this::_sendCommand);
			return true;
		}
		catch(Exception e) {handleOutException(e);}
		return false;
	}
	
	private void _sendCommand()
	{
		try{_sendCommand(command);}
		catch(Exception e) {handleOutException(e);}
	}
	
	private void _sendCommand(String s) throws Exception
	{
		out.println(s);
		if(s.equals("start")) commandStart();
		else if(s.equals("exit")) commandExit();
		else
		{
			if(session==null) commandStart();
			((P)session).p(s);
		}
	}
	
	
	
	private void commandStart() throws Exception
	{
		if(session!=null) throw new Exception("Session already created");
		
		InetAddress inetAddress = InetAddress.getByName(remoteIp);
		Socket socket = new Socket(inetAddress, remotePort);
		startSession(socket);
		
		String ip = socket.getRemoteSocketAddress().toString();
		out.println("session started with "+ip);
	}
	
	
	private void commandExit() throws Exception
	{
		if(session==null) throw new Exception("Session not found");
		endSession();
		out.println("session exited");
	}
	
	
	
	// SERVER EVENTS

	private void socketAccepted()
	{
		try
		{
			if(session!=null) throw new Exception("Session already created");
			
			Socket socket = (Socket) ((G) server).g();
			startSession(socket);
			
			String ip = socket.getRemoteSocketAddress().toString();
			in.println("Session started by "+ip);
		}
		catch(Exception e) {handleInException(e);}
	}


	private void exceptionCatched()
	{
		try
		{
			Exception exception = (Exception) ((R) server).r("exception");
			in.println("Exception occurred");
			exception.printStackTrace(in);
		}
		catch(Exception e) {handleInException(e);}
	}
	
	
	// SESSION EVENTS
	
	private void connectionClosed()
	{
		try
		{
			endSession();
			in.println("Session exited");
		}
		catch(Exception e)
		{Outside.err(this,"connectionClosed()",e);}
	}
	
	private void connectionStarted()
	{
		try
		{
			in.println("Session is running...");
		}
		catch(Exception e)
		{Outside.err(this,"connectionStarted()",e);}
	}
	
	private void messageReceived()
	{
		try
		{
			String input = (String) ((R)session).r("lastInput");
			in.println(input);
			
			if(input.startsWith(">")) handleInputChat(input.substring(1));
			if(input.startsWith("*")) handleInputExecute(input.substring(1));
			if(input.startsWith("%")) handleInputFile(input.substring(1));
		}
		catch(Exception e)
		{Outside.err(this,"messageReceived()",e);}
	}
	
	
	// EXCEPTIONS
	
	private void handleInException(Exception e)
	{
		in.println("Exception occurred");
		e.printStackTrace(in);
	}
	
	private void handleOutException(Exception e)
	{
		out.println("Exception occurred");
		e.printStackTrace(out);
	}
	
	
	// SESSION
	
	private void startSession(Socket socket) throws Exception
	{
		if(session!=null) throw new Exception("Session already started");
		session = buildSession.t(socket);
		((S) session).addActionListener(sessionListener);
	}
	
	private void endSession() throws Exception
	{
		if(session==null) throw new Exception("Session not found");
		((S)session).removeActionListener(sessionListener);
		((P)session).p("exit");
		session = null;
	}
	
	
	private void handleInputChat(String m) throws Exception
	{
		
	}
	
	private void handleInputExecute(String m) throws Exception
	{
		handleExecute.p(m);
	}
	
	private void handleInputFile(String m) throws Exception
	{
		String[] infos = m.split("\t");
		if(infos.length!=4) throw new Exception("Invalid info number: "+infos.length);
		
		String filePath = infos[0];
		long fileSize = Long.parseLong(infos[1]);
		
	}
}