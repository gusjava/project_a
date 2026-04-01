package a.entity.gus06.sys.gusserver1.handlesocket;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140704";}

	
	
	private Service serverOut;
	private Service executeCmd;

	private PrintStream out;
	
	public EntityImpl() throws Exception
	{
		serverOut = Outside.service(this,"gus06.sys.gusserver1.printstream");
		executeCmd = Outside.service(this,"gus06.command.execute");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Socket socket = (Socket) obj;
		InetAddress addr = socket.getInetAddress();
		
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		OutputStream os = socket.getOutputStream();
		PrintStream ps = new PrintStream(os);
		PrintStream ps1 = new PrintStream1(ps);

		out.println("connection started with addr: "+addr);
		
		String line = null;
		while((line = readLine(br)) != null)
		{
			out.println("<"+line);
			if(line.equals("shutdown"))
			{
				ps.println(TAG.CLOSED);
				close(socket);
				out.println("connection ended with addr: "+addr);
				return false;
			}
			
			serverOut.p(ps1);
			executeCmd(line);
			serverOut.p(null);
			
			ps.println(TAG.NEXT);
		}

		close(socket);
		out.println("connection ended with addr: "+addr);
		return true;
	}
	
	
	
	
	private void executeCmd(String line)
	{
		try{executeCmd.p(line);}
		catch(Exception e)
		{
			Outside.err(this,"executeCmd(String)",e);
			out.println("Error:"+e.getMessage());
		}
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
	
	
	
	
	
	
	
	
	
	private class PrintStream1 extends PrintStream
	{
		private PrintStream p;
		
	    public PrintStream1(PrintStream p)
	    {
	    	super(new OutputStreamNull());
	    	this.p = p;
	    }
	    
	    public void println()                   {println("");}
	    public void println(char[] val)         {println(new String(val));}
	    public void println(boolean val)        {println(""+val);}
	    public void println(char val)           {println(""+val);}
	    public void println(double val)         {println(""+val);}
	    public void println(float val)          {println(""+val);}
	    public void println(int val)            {println(""+val);}
	    public void println(long val)           {println(""+val);}
	    public void println(Object val)         {println(""+val);}
	    
	    public void println(String val)
	    {
	    	String[] n = val.split("[\n\r]+");
	    	for(int i=0;i<n.length;i++)
	    	p.println(TAG.LINE+n[i]);
	    }
	}
	
	private static class OutputStreamNull extends OutputStream
    {
        public OutputStreamNull(){} 
        public void write(int b){}
    }
}
