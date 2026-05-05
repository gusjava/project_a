package a.entity.gus06.command.transfer;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20140705";}


	private Service buildSocket;
	private Service cmdSocket;
	private Service fileToMd5;
	private Service transfer;
	private Service appJar;
	
	private PrintStream out;
	
	
	
	public EntityImpl() throws Exception
	{
		buildSocket = Outside.service(this,"gus06.command.transfer.socketbuilder");
		cmdSocket = Outside.service(this,"gus06.command.socket");
		fileToMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
		transfer = Outside.service(this,"gus06.io.transfer");
		appJar = Outside.service(this,"gus.x.app.location.asjar");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	
	
	
	public void e() throws Exception
	{
		File file = (File) appJar.g();
		out.println("sending app jar: "+file);
		transfert(file);
	}
	
	
	public void p(Object obj) throws Exception
	{
		File file = toFile(obj);
		if(!file.isFile()) throw new Exception("File does not exist: "+file);
		out.println("sending file: "+file);
		transfert(file);
	}

	
	
	private void transfert(File file) throws Exception
	{
		String md5 = (String) fileToMd5.t(file);
		cmdSocket.p("s transferwait "+md5);
		
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		
		try
		{
			socket = (Socket) buildSocket.g();
			os = socket.getOutputStream();
			is = new FileInputStream(file);
			
			transfer.p(new Object[]{is,os});
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
	
	
	
	
	
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
