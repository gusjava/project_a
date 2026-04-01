package a.entity.gus06.watching.lan;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, F {

	public String creationDate() {return "20150613";}

	public static final String LOOPBACK_ADDRESS = "127.0.0.1";
	public static final long PERIOD = 4000;
	
	
	private boolean isLan = false;

	private Timer timer;
	private TimerTask task;
	

	
	public EntityImpl() throws Exception
	{
		testLan();
		
		task = new TimerTask(){public void run() {testLan();}};
		
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),PERIOD);
	}
	
	
	
	
	private void testLan()
	{
		try
		{
			boolean isLan_ = isLan();
			
			if(isLan_ && !isLan)
			{
				isLan = true;
				online();
			}
			else if(!isLan_ && isLan)
			{
				isLan = false;
				offline();
			}
		}
		catch(Exception e)
		{Outside.err(this,"testLan()",e);}
	}
	
	
	
	private boolean isLan() throws UnknownHostException
	{return !InetAddress.getLocalHost().getHostAddress().equals(LOOPBACK_ADDRESS);}


	
	public boolean f(Object obj) throws Exception
	{return isLan;}
	
	
	
	private void online()
	{send(this,"online()");}
	
	private void offline()
	{send(this,"offline()");} 
}
