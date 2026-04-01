package a.entity.gus06.awt.component.support.locationonscreen;

import java.awt.Component;
import java.awt.Point;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160421";}

	public static final long DELAY = 50;
	
	private Timer timer;

	public EntityImpl() throws Exception
	{
		timer = new Timer("TIMER_"+getClass().getName());
	}


	public Object t(Object obj) throws Exception
	{return new CompHolder((Component) obj);}


	
	
	private class CompHolder extends S1 implements G, P
	{
		private Component comp;
		private TimerTask task;
		private Point p0;
		
		public CompHolder(Component comp)
		{
			this.comp = comp;
			task = new TimerTask() {public void run() {check();}};
			timer.schedule(task,new Date(),DELAY);
		}
		
		private void check()
		{
			if(!comp.isShowing())return;
			
			Point p = comp.getLocationOnScreen();
			if(p0==null || p.x!=p0.x || p.y!=p0.y)
			{positionChanged();p0 = p;}
		}
		
		private void positionChanged()
		{send(this,"positionChanged()");}
		
		public Object g() throws Exception
		{return comp;}

		public void p(Object obj) throws Exception
		{comp = (Component)obj;}
	}
}
