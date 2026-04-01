package a.entity.gus06.watching.mouse;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, R {

	public String creationDate() {return "20150626";}


	public static final long LAPSE = 50;

	
	private Service getTimer;
	private Timer timer;
	
	private Point p;
	private long t;
	private double d;
	private double v;


	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		
		p = MouseInfo.getPointerInfo().getLocation();
		t = System.currentTimeMillis();	
		d = 0;
		v = 0;
		
		TimerTask task = new TimerTask(){
			public void run(){update();}
		};
		timer.schedule(task,new Date(),LAPSE);
	}



	public Object r(String key) throws Exception
	{
		if(key.equals("t")) return ""+t;
		if(key.equals("x")) return ""+p.x;
		if(key.equals("y")) return ""+p.y;
		if(key.equals("d")) return ""+d;
		if(key.equals("v")) return ""+v;
		if(key.equals("p")) return p;
		
		if(key.equals("keys")) return new String[]{"t","x","y","d","v","p"};
		
		throw new Exception("Unknown key: "+key);
	}


	
	
	
	private void update()
	{
		Point p0 = MouseInfo.getPointerInfo().getLocation();
		long t0 = System.currentTimeMillis();	
		
		int dx = p0.x - p.x;
		int dy = p0.y - p.y;
		
		double d0 = Math.sqrt(dx*dx + dy*dy);
		double v0 = d0/(t0 - t);
		
		if(v==0 && v0==0) return;
		
		p = p0;
		t = t0;
		d = d0;
		v = v0;
		
		mouseMoved();
	}
	
	
	
	
	private void mouseMoved()
	{send(this,"mouseMoved()");}
}
