package a.entity.gus06.time.execute.delay.ms;

import a.framework.*;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20180226";}


	private Service getTimer;
	private Timer timer;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E execute = (E) o[0];
		long t = toLong(o[1]);
		
		return new E1(execute,t);
	}
	
	
	public void p(Object obj) throws Exception
	{((E) t(obj)).e();}
	
	
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}
	
	
	
	private class E1 implements E
	{
		private E e;
		private long t;
		
		public E1(E e, long t)
		{
			this.e = e;
			this.t = t;
		}
		
		public void e() throws Exception
		{
			TimerTask task = new TimerTask()
			{public void run() {perform();}};
			
			timer.schedule(task,t);
		}
		
		private void perform()
		{execute(e);}
	}
	
	
	private void execute(E execute)
	{
		try{execute.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}
