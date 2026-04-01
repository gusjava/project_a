package a.entity.gus06.swing.button.delaysupport.click;

import a.framework.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170507";}

	private long delay = 400;
	
	private Service getTimer;
	private Timer timer;
	
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	

	public Object t(Object obj) throws Exception
	{return new DelaySupport(delay,(JButton)obj);}

	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("delay"))
		{delay = Long.parseLong((String)obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private class DelaySupport extends S1 implements ActionListener
	{
		private long delay;
		private TimerTask task;
		
		public DelaySupport(long delay, JButton button)
		{
			this.delay = delay;
			button.addActionListener(this);
		}
	
		public void actionPerformed(ActionEvent e)
		{
			if(task!=null) task.cancel();
			task = new TimerTask(){public void run() {clicked();}};
			timer.schedule(task,delay);
		}
		
		private void clicked()
		{send(this,"clicked()");}
	}
}
