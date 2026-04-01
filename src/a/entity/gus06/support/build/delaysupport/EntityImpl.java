package a.entity.gus06.support.build.delaysupport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl implements Entity, T, V {

	public String creationDate() {return "20160421";}

	private long delay = 400;

	private Service getTimer;
	private Timer timer;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("delay"))
		{delay = Long.parseLong((String)obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}



	public Object t(Object obj) throws Exception
	{return new DelaySupport((S)obj);}


	
	private class DelaySupport extends S1 implements ActionListener
	{
		private S sup;
		private TimerTask task;
		
		public DelaySupport(S sup) throws Exception
		{
			this.sup = sup;
			sup.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e)
		{
			if(task!=null) task.cancel();
			task = new TimerTask(){public void run() {perform();}};
			timer.schedule(task,delay);
		}
		
		private void perform()
		{send(this,"perform()");}
	}
}
