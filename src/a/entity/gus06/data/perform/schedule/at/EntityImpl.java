package a.entity.gus06.data.perform.schedule.at;

import a.framework.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160924";}


	private Service getTimer;
	private Service findDate;
	private Service findTimerTask;

	private Timer timer;
	
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		findTimerTask = Outside.service(this,"gus06.find.timertask");
		findDate = Outside.service(this,"gus06.find.date");
		
		timer = (Timer) getTimer.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object execute = o[0];
		Object data = o[1];
		
		if(data instanceof List)
		{
			List list = (List) data;
			for(Object element:list) schedule(execute,element);
			return;
		}
		
		if(data instanceof Set)
		{
			Set set = (Set) data;
			for(Object element:set) schedule(execute,element);
			return;
		}
		
		if(data instanceof Date[])
		{
			Date[] dates = (Date[]) data;
			for(Date date:dates) schedule(execute,date);
			return;
		}
		
		schedule(execute,data);
	}
	
	
	private void schedule(Object execute, Object data) throws Exception
	{
		TimerTask task = (TimerTask) findTimerTask.t(execute);
		Date date = (Date) findDate.t(data);
		
		timer.schedule(task,date);
	}
}
