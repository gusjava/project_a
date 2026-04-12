package a.entity.gus06.command.transferwait;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import a.framework.*;

public class EntityImpl implements Entity, P, E, Runnable {

	public String creationDate() {return "20140705";}


	private Service ioTransfer;
	private Service fileToMd5;
	private Service buildThread;
	
	private PrintStream out;
	private File storeDir;
	private String port;
	
	private ServerSocket serverSocket;
	private File file;
	private Thread t;
	
	
	
	
	public EntityImpl() throws Exception
	{
		ioTransfer = Outside.service(this,"gus06.io.transfer");
		fileToMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		buildThread = Outside.service(this,"gus.x.thread.wrap1");
		
		out = (PrintStream) Outside.resource(this,"sysout");
		port = (String) Outside.resource(this,"property#server.transfert.port");
		storeDir = (File) Outside.resource(this,"g#gus.path.root.received");
		
		if(port==null) throw new Exception("Server port is undefined");
	}
	
	
	
	public void e() throws Exception
	{
		if(t!=null && t.isAlive())
			out.println("file transfer is running");
		else out.println("file transfer is not running");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive())
		{
			out.println("file transfer is already running");
			return;
		}
		
		String md5 = (String) obj;
		file = new File(storeDir,md5);
		
		serverSocket = new ServerSocket(Integer.parseInt(port));
		out.println("file transfer initialized on port: "+port);
		
		t = (Thread) buildThread.t(this);
		t.start();
	}

	
	

	public void run()
	{
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		
		try
		{
			socket = serverSocket.accept();
			is = socket.getInputStream();
			os = new FileOutputStream(file);
			
			ioTransfer.p(new Object[]{is,os});
			
			String md5 = (String) fileToMd5.t(file);
			if(!file.getName().equals(md5))
				throw new Exception("Invalid md5 cache file: "+file.getName()+" [md5="+md5+"]");
		}
		catch(Exception e)
		{
			String message = "Failed to transfert file: "+file;
			Outside.err(this,"run()", new Exception(message,e));
		}
		finally
		{
			close(os);
			close(is);
			close(socket);
		}
	}

	
	
	
	private void close(Closeable c)
	{
		if(c==null) return;
		try{c.close();} catch(IOException e){}
	}
}