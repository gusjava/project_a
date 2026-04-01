package a.entity.gus06.sys.colorpicker1.holder.instant.pixel9;

import java.awt.Color;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G {

	public String creationDate() {return "20180226";}

	public static final long LAPSE = 50;

	
	private Service pickColor;

	private Timer timer;
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
			color = (Color) pickColor.g();
			colorPicked();
		}
		catch(Exception e)
		{Outside.err(this,"pick()",e);}
	}
	
	
	private void colorPicked()
	{send(this,"colorPicked()");}
}
