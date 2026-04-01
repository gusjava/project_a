package a.entity.gus06.sys.socket1.build.session;

import a.framework.*;
import java.net.Socket;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.Date;
import java.io.InputStreamReader;
import java.io.IOException;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221119";}

	public static String CHARSET = "UTF-8";
	public static String CMD_EXIT = "exit";
	
	
	private Service buildThread;

	public EntityImpl() throws Exception
	{
		buildThread = Outside.service(this,"gus06.thread.manager");
	}
	
	public Object t(Object obj) throws Exception
	{
		return new Session((Socket) obj);
	}
	
	
	
	private class Session extends S1 implements P, R, Runnable
	{
		private Socket socket;
		private BufferedReader in;
		private PrintStream out;
		private Thread t;
		
		private Date startDate;
		private Date endDate;
		private String name;
		
		private String lastInput;
		private String lastOutput;
		
		
		public Session(Socket socket) throws Exception
		{
			this.socket = socket;
			name = socket.getInetAddress().getHostAddress();
			
			//create input
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, CHARSET);
			in = new BufferedReader(isr);
			
			//create output
			OutputStream os = socket.getOutputStream();
			out = new PrintStream(os, true, CHARSET);
			
			t = (Thread) buildThread.t(this);
			t.start();
		}
		
		
		
		public void p(Object obj) throws Exception
		{
			String command = (String) obj;
			
			if(command.equals(CMD_EXIT))
			{
				out.println(CMD_EXIT);
				exit();
			}
			else send(command);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("name")) return name;
			if(key.equals("startdate")) return startDate;
			if(key.equals("enddate")) return endDate;
			
			if(key.equals("lastInput")) return lastInput;
			if(key.equals("lastOutput")) return lastOutput;
			
			if(key.equals("keys"))
			return new String[]{"name", "startdate", "enddate", "lastInput", "lastOutput"};
			throw new Exception("Unknown key: "+key);
		}
		
		
		
		private void send(String m) throws Exception
		{
			if(t==null) throw new Exception("Session is over");
			
			lastOutput = m;
			out.println(m);
			messageSent();
		}
		
		private void received(String m)
		{
			lastInput = m;
			messageReceived();
		}
		
		
		private void exit() throws Exception
		{
			if(t==null) return;
			
			
			in.close();
			out.close();
			
			t = null;
			in = null;
			out = null;
		}
		
		
		
		public void run()
		{
			try
			{
				startDate = new Date();
				connectionStarted();
				
				String line;
				while((line=in.readLine())!=null)
				{
					received(line);
					if(line.equals(CMD_EXIT)) break;
					Thread.yield();
				}
			}
			catch(IOException e){}
			
			endDate = new Date();
			connectionClosed();
		}

		
        
		private void connectionStarted()
		{send(this,"connectionStarted()");}
		
		private void connectionClosed()
		{send(this,"connectionClosed()");}
		
		private void messageReceived()
		{send(this,"messageReceived()");}
		
		private void messageSent()
		{send(this,"messageSent()");}
	}
}