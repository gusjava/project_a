package a.entity.gus.x.socket.server.wrap1;

import a.framework.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20221110";}

	
	public Object t(Object obj) throws Exception
	{
		ServerSocket server = (ServerSocket) obj;
		return new Holder(server);
	}
	
	private class Holder extends S1 implements Runnable, G, R, P
	{
		private ServerSocket server;
		private Exception exception;
		private Socket lastSocket;
		private int numberAccepted;
		
		public Holder(ServerSocket server) throws Exception
		{
			this.server = server;
			numberAccepted = 0;
		}
		
		public Object g() throws Exception
		{return lastSocket;}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("server")) return server;
			if(key.equals("exception")) return exception;
			if(key.equals("lastSocket")) return lastSocket;
			if(key.equals("numberAccepted")) return Integer.valueOf(numberAccepted);
			
			if(key.equals("keys")) return new String[]{"server", "exception", "lastSocket", "numberAccepted"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void p(Object obj) throws Exception
		{
			String s = (String) obj;
			if(s.equals("close")) {server.close();return;}
			throw new Exception("Unsupported command: "+s);
		}
		
		public void run()
		{
			try
			{
				while(!server.isClosed())
				waitingForNextConnection();
			}
			catch(Exception e)
			{
				exception = e;
				exceptionCatched();
			}
		}
		
		private void waitingForNextConnection() throws Exception
		{
			lastSocket = server.accept();
			numberAccepted++;
			socketAccepted();
		}
		
		private void exceptionCatched()
		{send(this,"exceptionCatched()");}
		
		private void socketAccepted()
		{send(this,"socketAccepted()");}
	}
}