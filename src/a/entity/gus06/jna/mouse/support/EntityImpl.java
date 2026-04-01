package a.entity.gus06.jna.mouse.support;

import a.framework.*;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class EntityImpl extends S1 implements Entity, G, F, Runnable {

	public String creationDate() {return "20200113";}



	private Service keyboard;
	
	private ArrayBlockingQueue queue;
	private Thread t;
	
	private String action;
	private String position;
	private String button;
	
	private Set set;


	public EntityImpl() throws Exception
	{
		keyboard = Outside.service(this,"gus06.jna.mouse.queue");
		queue = (ArrayBlockingQueue) keyboard.g();
		set = new HashSet();
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public Object g() throws Exception
	{return position;}
	
	public boolean f(Object obj) throws Exception
	{return set.contains(obj);}
	
	
	
	private void rightButtonPressed()
	{send(this,"rightButtonPressed()");}
	
	private void rightButtonReleased()
	{send(this,"rightButtonReleased()");}
	
	
	
	private void leftButtonPressed()
	{send(this,"leftButtonPressed()");}
	
	private void leftButtonReleased()
	{send(this,"leftButtonReleased()");}
	
	
	
	private void middleButtonPressed()
	{send(this,"middleButtonPressed()");}
	
	private void middleButtonReleased()
	{send(this,"middleButtonReleased()");}
	
	
	
	
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
			String[] n = info.split(":");
			action = n[0];
			position = n[1];
			
			if(action.startsWith("+"))
			{
				button = action.substring(1);
				set.add(button);
				
				if(button.equals("R")) rightButtonPressed();
				else if(button.equals("L")) leftButtonPressed();
				else if(button.equals("M")) middleButtonPressed();
			}
			else if(action.startsWith("-"))
			{
				button = action.substring(1);
				set.remove(button);
				
				if(button.equals("R")) rightButtonReleased();
				else if(button.equals("L")) leftButtonReleased();
				else if(button.equals("M")) middleButtonReleased();
			}
			else throw new Exception("Invalid action: "+action);
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
