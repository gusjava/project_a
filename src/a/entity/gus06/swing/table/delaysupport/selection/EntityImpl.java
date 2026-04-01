package a.entity.gus06.swing.table.delaysupport.selection;

import java.util.Timer;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import a.framework.*;
import java.util.TimerTask;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231118";}

	private long delay = 400;
	
	private Service getTimer;
	private Timer timer;
	
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	

	public Object t(Object obj) throws Exception
	{return new DelaySupport(delay,(JTable)obj);}

	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("delay"))
		{delay = Long.parseLong((String)obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private class DelaySupport extends S1 implements ListSelectionListener
	{
		private long delay;
		private TimerTask task;
		
		public DelaySupport(long delay, JTable table)
		{
			this.delay = delay;
			table.getSelectionModel().addListSelectionListener(this);
		}
	
		public void valueChanged(ListSelectionEvent e)
		{
			if(task!=null) task.cancel();
			task = new TimerTask(){public void run() {selectionChanged();}};
			timer.schedule(task,delay);
		}
		
		private void selectionChanged()
		{send(this,"selectionChanged()");}
	}
}