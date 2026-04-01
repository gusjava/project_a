package a.entity.gus06.jna.keyboard.support;

import a.framework.*;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class EntityImpl extends S1 implements Entity, G, F, Runnable {

	public String creationDate() {return "20190228";}



	private Service keyboard;
	
	private ArrayBlockingQueue queue;
	private Thread t;
	
	private String code;
	private Set set;


	public EntityImpl() throws Exception
	{
		keyboard = Outside.service(this,"gus06.jna.keyboard.queue");
		queue = (ArrayBlockingQueue) keyboard.g();
		set = new HashSet();
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public Object g() throws Exception
	{return code;}
	
	public boolean f(Object obj) throws Exception
	{return set.contains(obj);}
	
	
	private void keyPressed()
	{send(this,"keyPressed()");}
	
	private void keyReleased()
	{send(this,"keyReleased()");}
	
	
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
			if(info.startsWith("+"))
			{
				code = info.substring(1);
				set.add(code);
				keyPressed();
			}
			else if(info.startsWith("-"))
			{
				code = info.substring(1);
				set.remove(code);
				keyReleased();
			}
			else throw new Exception("Invalid info: "+info);
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
}
