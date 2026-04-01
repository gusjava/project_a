package a.entity.gus06.mouse.watcher;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, R {

	public String creationDate() {return "20161010";}


	public static final long LAPSE = 50;

	
	private Timer timer;
	
	private Point p0;
	private long t0;
	private double d0;
	private double v0;


	public EntityImpl() throws Exception
	{
		p0 = MouseInfo.getPointerInfo().getLocation();
		t0 = System.currentTimeMillis();	
		d0 = 0;
		v0 = 0;
		
		TimerTask task = new TimerTask(){
			public void run(){update();}
		};
		
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}



	public Object r(String key) throws Exception
	{
		if(key.equals("t")) return ""+t0;
		if(key.equals("x")) return ""+p0.x;
		if(key.equals("y")) return ""+p0.y;
		if(key.equals("d")) return ""+d0;
		if(key.equals("v")) return ""+v0;
		if(key.equals("p")) return p0;
		
		if(key.equals("keys")) return new String[]{"t","x","y","d","v","p"};
		
		throw new Exception("Unknown key: "+key);
	}


	
	private void update()
	{
		Point p = MouseInfo.getPointerInfo().getLocation();
		long t = System.currentTimeMillis();	
		
		
		int dx = p.x - p0.x;
		int dy = p.y - p0.y;
		double dt = t - t0;
		
		double d = Math.sqrt(dx*dx + dy*dy);
		double v = d/dt;
		
		if(v==0 && v0==0) return;
		
		p0 = p;
		t0 = t;
		d0 = d;
		v0 = v;
		mouseMoved();
	}
	
	
	private void mouseMoved()
	{send(this,"mouseMoved()");}
}
