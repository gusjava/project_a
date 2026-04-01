package a.entity.gus06.sys.clipboardwatcher1.engine;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;
import java.util.Objects;

public class EntityImpl extends S1 implements Entity, G, P {

	public String creationDate() {return "20180409";}

	
	public static final long LAPSE = 100;


	private Service clipboard;
	private String text;
	
	private Service getTimer;
	private Timer timer;
	private TimerTask task;
	

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string.or.filepaths");
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		
		text = (String) clipboard.g();
		
		task = new TimerTask(){
			public void run(){check();}
		};
		timer.schedule(task,new Date(),LAPSE);
	}

	
	
	private void check()
	{
		try
		{
			String text0 = (String) clipboard.g();
			if(text0==null) return;
			
			if(!Objects.equals(text0,text))
			{
				text = text0;
				clipboardChanged();
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"check()",e);
			task.cancel();
		}
	}
	

	
	public Object g() throws Exception
	{return text;}
	
	
	public void p(Object obj) throws Exception
	{clipboard.p(obj);}

	
	private void clipboardChanged()
	{send(this,"clipboardChanged()");}
}
