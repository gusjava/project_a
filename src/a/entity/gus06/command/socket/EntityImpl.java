package a.entity.gus06.command.socket;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import a.framework.*;


public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20140704";}
	
	public static final String CLOSED = "!";
	public static final String LINE = ":";


	private Service buildSocket;
	private PrintStream out;
	
	private Socket socket;
	private PrintStream ps;
	private BufferedReader br;
	
	

	public EntityImpl() throws Exception
	{
		buildSocket = Outside.service(this,"gus06.command.socket.socketbuilder");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String cmd = (String) obj;
		
		String[] n = cmd.split(" ",2);
		String action = n[0];
		String info = n.length==2?n[1]:null;
		
		if(action.equals("open"))
		{
			open(info);
			return;
		}
		if(action.equals("close"))
		{
			closeAll();
			return;
		}
		if(action.equals("state"))
		{
			state();
			return;
		}
		if(action.equals("s"))
		{
			send(info);
			return;
		}
		throw new Exception("Unsupported socket command: "+cmd);
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{return send((String) obj);}
	
	
	
	
	
	
	
	
	private String send(String info) throws Exception
	{
		if(!isValid()) open(null);
		ps.println(info);

		StringBuffer b = new StringBuffer();
		String line = null;
		while((line = readLine(br)) != null && line.startsWith(LINE))
		{
			b.append(line.substring(1)+"\n");
			println("<"+line.substring(1));
		}
		
		if(line==null || line.equals(CLOSED)) closeAll();
		return b.toString().trim();
	}
	

	
	
	
	
	private void state() throws Exception
	{
		if(socket==null) println("socket not initialized");
		else if(socket.isClosed()) println("socket closed");
		else println("socket started");
	}
	
	
	
	
	private void open(String info) throws Exception
	{
		closeAll();
		socket = (Socket) buildSocket.t(info);
		
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		OutputStream os = socket.getOutputStream();
		ps = new PrintStream(os);
	}
	
	
	
	
	private void closeAll()
	{
		if(ps!=null) {close(ps);ps = null;}
		if(br!=null) {close(br);br = null;}
		if(socket!=null) {close(socket);socket = null;}
	}
	
	
	private void println(String m) throws Exception
	{out.println(m);}
	
	
	
	private boolean isValid()
	{
		if(socket==null) return false;
		if(socket.isClosed()) return false;
		if(socket.isInputShutdown()) return false;
		if(socket.isOutputShutdown()) return false;
		return socket.isConnected();
	}

	
	
	private void close(Closeable closeable)
	{
		try{closeable.close();}
		catch(IOException e){}
	}
	
	
	
	
	private String readLine(BufferedReader br)
	{
		try{return br.readLine();}
		catch(IOException e)
		{return null;}
	}
}
