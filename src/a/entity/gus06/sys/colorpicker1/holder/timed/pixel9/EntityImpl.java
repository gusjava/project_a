package a.entity.gus06.sys.colorpicker1.holder.timed.pixel9;

import java.awt.Color;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G {

	public String creationDate() {return "20180226";}

	public static final long LAPSE = 10;
	public static final long NUMBER = 10;

	
	private Service pickColor;

	private Timer timer;
	private int count = 0;
	private Color c0;
	private Color color;
	

	public EntityImpl() throws Exception
	{
		pickColor = Outside.service(this,"gus06.sys.colorpicker1.pick.pixel9");
		
		TimerTask task = new TimerTask(){
			public void run(){pick();}
		};
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}



	public Object g() throws Exception
	{return color;}

	
	
	
	private void pick()
	{
		try
		{
			Color c = (Color) pickColor.g();
			if(equals(c,c0)) count++;
			else resetC(c);
			
			if(count>=NUMBER)
			{
				color = c;
				colorPicked();
			}
		}
		catch(Exception e)
		{Outside.err(this,"pick()",e);}
	}
	
	
	
	private void resetC(Color c)
	{
		c0 = c;
		count = 0;
	}

	
	
	private void colorPicked()
	{send(this,"colorPicked()");}
	
	
	
	
	private boolean equals(Color c1, Color c2)
	{
		if(c1==null || c2==null) return false;
		return c1.equals(c2);
	}
}