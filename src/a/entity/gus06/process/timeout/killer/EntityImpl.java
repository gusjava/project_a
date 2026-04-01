package a.entity.gus06.process.timeout.killer;

import a.framework.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140725";}


	private PrintStream out;
	private Timer timer;

	public EntityImpl() throws Exception
	{
		out = (PrintStream) Outside.resource(this,"sysout");
		timer = new Timer("TIMER_"+getClass().getName());
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

		Process pr = (Process) o[0];
		Long lapse = (Long) o[1];
		
		if(pr==null) throw new Exception("Process null");
		if(lapse==null) throw new Exception("Lapse null");

		Killer k = new Killer(pr,lapse);
		pr.waitFor();
		k.cancel();
	}



	public class Killer extends TimerTask
	{
		private Process pr;
		private long t1;
		
		public Killer(Process pr, Long lapse)
		{
			this.pr = pr;
			timer.schedule(this,lapse.longValue());
			t1 = System.currentTimeMillis();
		}
		
		public void run()
		{
			long dt = System.currentTimeMillis() - t1;
			pr.destroy();
			out.println("Timeout reached: process have been destroyed (dt="+dt+" ms)");
		}
	}

}
