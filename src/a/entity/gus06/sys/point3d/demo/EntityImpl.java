package a.entity.gus06.sys.point3d.demo;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160421";}
	
	
	public static final long LAPSE = 100;
	public static final int NUMBER = 1500;
	public static final int PSIZE = 3;
	public static final int RAYON_SPHERE = 80;
	public static final int EDGE_CUBE = 60;
	public static final int DISTANCE = 300;
	public static final double VISION = 0.12;
	public static final double ANGLE = 0.1;
	public static final Color FOREGROUND = Color.CYAN;
	public static final Color BACKGROUND = Color.BLACK;
	public static final int CYCLE = 600;
	
	
	private Service screen1;
	private Service engine;
	private Service generator;
	
	private JPanel panel;
	
	private Timer timer;
	private TimerTask task;
	
	private List sphere;
	private List cube;
	private List current;
	
	
	private Color color;
	private Color colorTarget;
	private int index;



	public EntityImpl() throws Exception
	{
		screen1 = Outside.service(this,"gus06.sys.point3d.screen1");
		engine = Outside.service(this,"gus06.sys.point3d.engine1");
		generator = Outside.service(this,"gus06.sys.point3d.listgenerator");

		sphere = (List) generator.t("sphere "+NUMBER+" "+RAYON_SPHERE);
		cube = (List) generator.t("cube "+NUMBER+" "+EDGE_CUBE);
		
		operateList("translate 0;"+DISTANCE+";0");
		
		color = FOREGROUND;
		current = sphere;
		
		screen1.v("vision",""+VISION);
		screen1.v("pixelColor",color);
		screen1.v("pixelSize",""+PSIZE);
		screen1.p(sphere);
		
		panel = (JPanel) screen1.i();
		
		panel.setBackground(Color.BLACK);
		
		index = 0;
		task = new TimerTask() {public void run(){update();}};
		
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}



	public Object i() throws Exception
	{return panel;}


	
	private void update()
	{
		try
		{
			operateList("translate 0;-"+DISTANCE+";0");
			operateList("rotate_OZ "+ANGLE);
			operateList("translate 0;"+DISTANCE+";0");
			
			index++;
			if(index==CYCLE)
			{
				index = 0;
				current = current==cube?sphere:cube;
			}
			
			changePixelColor();
			screen1.v("pixelColor",color);
			screen1.p(current);
		}
		catch(Exception e)
		{
			Outside.err(this,"update()",e);
			task.cancel();
		}
	}
	
	
	
	
	private void operateList(String rule) throws Exception
	{
		engine.v(rule,sphere);
		engine.v(rule,cube);
	}
	
	
	
	
	
	private void changePixelColor()
	{
		if(colorTarget==null)
			colorTarget = new Color(rand(256),rand(256),rand(256));
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		int n = (int)(Math.random()*3);
		switch(n)
		{
			case 0:
				if(r>colorTarget.getRed())r--;
				else if(r<colorTarget.getRed())r++;
				break;
			case 1:
				if(g>colorTarget.getGreen())g--;
				else if(g<colorTarget.getGreen())g++;
				break;
			case 2:
				if(b>colorTarget.getBlue())b--;
				else if(b<colorTarget.getBlue())b++;
				break;
		}
		if(r==colorTarget.getRed() && g==colorTarget.getGreen() && b==colorTarget.getBlue())
			colorTarget = null;
		color = new Color(r,g,b);
	}
	
	private int rand(int n)
	{return (int)(Math.random()*n);}
}