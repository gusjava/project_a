package a.entity.gus06.jna.keyboard.queue.mock.port4568;

import a.framework.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.List;
import java.util.Arrays;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, G, Runnable {

	public String creationDate() {return "20190413";}
	
	public static final String INFO = "localhost:4568";
	public static final Charset CHARSET = Charset.forName("UTF-8");
	
	
	
	private Service builder;
	
	private ArrayBlockingQueue queue;
	private Thread t;


	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.socket.builder1");
		
		queue = new ArrayBlockingQueue(100);
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{while(true) perform();}
	
	
	
	private void perform()
	{
		try
		{
			Socket socket = (Socket) builder.t(INFO);
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,CHARSET);
			BufferedReader in = new BufferedReader(isr);
			
			String line = null;
			while((line=in.readLine())!=null)
			{
				put(line);
				Thread.yield();
			}
		}
		catch(Exception e) {}
	}
	
	
	public Object g() throws Exception
	{return queue;}
	
	
	private void put(String info)
	{
		try{queue.put(info);}
		catch (InterruptedException e){}
	}
}
