package a.entity.gus06.appli.keyboardserver.manager;

import a.framework.*;
import java.util.concurrent.ArrayBlockingQueue;

public class EntityImpl extends S1 implements Entity, Runnable {

	public String creationDate() {return "20190413";}

	

	private Service keyboard;
	private Service server;
	
	private ArrayBlockingQueue queue;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		keyboard = Outside.service(this,"gus06.jna.keyboard.queue.jna");
		server = Outside.service(this,"gus06.appli.keyboardserver.server4568");
		
		queue = (ArrayBlockingQueue) keyboard.g();
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{while(true) perform();}
	
	
	
	private void perform()
	{
		String info = nextInfo();
		if(info!=null) handleInfo(info);
	}
	
	
	private void handleInfo(String info)
	{
		try
		{
			server.p(info);
			infoReceived();
		}
		catch(Exception e)
		{Outside.err(this,"handleInfo(String)",e);}
	}
	
	
	private String nextInfo()
	{
		try{return (String) queue.take();}
		catch(InterruptedException e) {}
		return null;
	}
	
	private void infoReceived()
	{send(this,"infoReceived()");}
}
