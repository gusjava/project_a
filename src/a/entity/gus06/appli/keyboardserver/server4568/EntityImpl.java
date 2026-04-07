package a.entity.gus06.appli.keyboardserver.server4568;

import a.framework.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Iterator;
import java.nio.charset.Charset;
import java.io.PrintStream;

public class EntityImpl implements Entity, P, Runnable {

	public String creationDate() {return "20190414";}

	public static final int PORT = 4568;
	public static final Charset CHARSET = Charset.forName("UTF-8");
	
	
	private Service buildServer;
	
	private PrintStream out;
	private ServerSocket serverSocket;
	private Vector pwList;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		buildServer = Outside.service(this,"gus.x.socket.server.build");
		out = (PrintStream) Outside.resource(this,"sysout");
		
		serverSocket = (ServerSocket) buildServer.t(PORT);
		
		pwList = new Vector();
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{while(true) perform();}
	
	
	private void perform()
	{
		try
		{
			Socket socket = serverSocket.accept();
			out.println("new socket accepted: "+socket);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os,CHARSET);
			PrintWriter pw = new PrintWriter(new BufferedWriter(osw),true);
			
			pwList.add(pw);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	public void p(Object obj) throws Exception
	{
		String info = (String) obj;
		
		Iterator it = pwList.iterator();
		while(it.hasNext())
		{
			PrintWriter pw = (PrintWriter) it.next();
			println(pw,info);
		}
	}
	
	
	private void println(PrintWriter pw, String info)
	{
		pw.println(info);
	}
	
}
