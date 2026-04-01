package a.entity.gus06.socket.build.holder;

import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Date;
import java.io.*;
import a.framework.*;

public class EntityImpl implements Entity, T, V {

	public String creationDate() {return "20180312";}
	
	private Charset charset = null;
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("charset")) {charset = (Charset) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Socket socket = (Socket) obj;
		if(charset==null) return new Holder(socket);
		return new Holder(socket,charset);
	}
	


	public class Holder extends S1 implements R, V, P, G, Runnable
	{
		public static final String END = "#END#";
		
		private Charset charset = Charset.forName("UTF-8");
	
		private Socket socket;
		private String ip;
		
		private PrintWriter out;
		private BufferedReader in;
		
		private String lastReceivedLine;
		private String lastSentLine;
		
		private Date lastReceivedDate;
		private Date lastSentDate;
		
		private boolean connectionOver;
		private boolean disconnected;
		
		private boolean inputActivated;
		private boolean outputActivated;
		
		private Date startDate;
		private Date endDate;
		
		
		public Holder(Socket socket, Charset charset)
		{
			this.socket = socket;
			this.charset = charset;
		}
	   
		public Holder(Socket socket)
		{
			this.socket = socket; 
		}
		
		public void run()
		{
			perform();
			
			endDate = new Date();
			connectionOver = true;
			
			if(disconnected) connectionClosed();
			else connectionLost();
			listeners().clear();
		}
		
		public void p(Object obj) throws Exception
		{sendMessage((String) obj);}
		
		public Object g() throws Exception
		{return lastReceivedLine;}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("ip"))			return ip;
			if(key.equals("id"))			return ip;
			if(key.equals("over"))			return ""+connectionOver;
			if(key.equals("disconnected"))		return ""+disconnected;
			if(key.equals("inputactivated"))	return ""+inputActivated;
			if(key.equals("outputactivated"))	return ""+outputActivated;
			
			if(key.equals("lastreceivedline"))	return lastReceivedLine;
			if(key.equals("lastsentline"))		return lastSentLine;
			if(key.equals("lastreceiveddate"))	return lastReceivedDate;
			if(key.equals("lastsentdate"))		return lastSentDate;
			
			if(key.equals("startdate"))		return startDate;
			if(key.equals("enddate"))		return endDate;
			
			
			if(key.equals("keys"))
				return new String[]{"ip","over",
					"disconnected",
					"inputactivated",
					"outputactivated",
					"lastreceivedline",
					"lastsentline",
					"lastreceiveddate",
					"lastsentdate",
					"startdate",
					"enddate"};
			
			throw new Exception("Unknown key: "+key);
		}
	
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("inputactivated"))
			{inputActivated = ((Boolean)obj).booleanValue();return;}
			
			if(key.equals("outputactivated"))
			{outputActivated = ((Boolean)obj).booleanValue();return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		
		
		
		
		private void perform()
		{
			try
			{
				init();
				connectionStarted();
				readLines();
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"perform()",e);}
		}
		
		private void init() throws IOException
		{
			startDate = new Date();
			ip = socket.getInetAddress().getHostAddress();
			
			//create input
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,charset);
			in = new BufferedReader(isr);
			
			//create output
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osr = new OutputStreamWriter(os,charset);
			out = new PrintWriter(new BufferedWriter(osr),true);
			
			connectionOver = false;
			disconnected = false;
			
			inputActivated = true;
			outputActivated = true;
		}
		
		private void readLines()
		{
			try
			{
				String line = null;
				while((line=in.readLine())!=null)
				{received(line);Thread.yield();}
			}
			catch(IOException e){}
		}
		
		private void received(String message)
		{
			if(!outputActivated) return;
			
			lastReceivedLine = message;
			lastReceivedDate = new Date();
			messageReceived();
			
			if(message.equals(END)) disconnect();
		}
		
		private synchronized void sendMessage(String message) throws IOException
		{
			if(!inputActivated)return;
			
			lastSentLine = message;
			lastSentDate = new Date();
			out.println(message);
			messageSent();
			
			if(message.equals(END)) disconnect();
		}
		
		private void disconnect()
		{
			disconnected = true;
			try{socket.close();}
			catch(Exception e){}
		}
		
		
		
		
		private void connectionStarted()
		{send(this,"connectionStarted()");}
		
		private void connectionClosed()
		{send(this,"connectionClosed()");}
		
		private void connectionLost()
		{send(this,"connectionLost()");}
		 
		private void messageReceived()
		{send(this,"messageReceived()");}
		
		private void messageSent()
		{send(this,"messageSent()");}
	}
}
