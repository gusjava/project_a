package a.entity.gus06.sys.gameengine1.mainpanel;

import java.awt.BorderLayout;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I, Runnable, E, P {

	public String creationDate() {return "20200515";}

	public static int FPS = 60;
	public static int ERRMAX = 20;
	
	
	private Service screen;
	private Service producer;
	private Service control;
	
	private Thread t;
	private boolean interrupted = false;
	
	private int errNumber = 0;
	private long period_ns = (long) (1_000_000_000 / (double) FPS);
	
	
	
	private JPanel panel;
	private JLabel label;
	private JComponent screenComp;
	
	
	

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		producer = Outside.service(this,"gus06.sys.gameengine1.producer");
		control = Outside.service(this,"gus06.sys.gameengine1.control");
		
		panel = new JPanel(new BorderLayout());
		label = new JLabel(" ");
		screenComp = (JComponent) screen.i();
		
		panel.add(screenComp,BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		control.p(screenComp);
	}

	

	public Object i() throws Exception
	{return panel;}
	
	
	

	public void p(Object obj) throws Exception
	{producer.p(obj);}
	



	
	public void run()
	{
		interrupted = false;
		screenComp.requestFocusInWindow();
		
		while(errNumber<ERRMAX && !interrupted)
		{
			long start = System.nanoTime();
			updateScreen();
			long duration_ns = System.nanoTime()-start;
			
			long left = period_ns - duration_ns;
			if(left>0) sleep_ns(left);
			
			Thread.yield();
			
			label.setText(" FPS:"+FPS+" cycle:"+period_ns+" sleep:"+left+" active:"+duration_ns);
		}
	}
	
	
	
	
	
	private void updateScreen()
	{
		try
		{
			Object image = producer.g();
			screen.p(image);
		}
		catch(Exception e)
		{
			errNumber++;
			Outside.err(this,"updateScreen()",e);
		}
	}
	
	
	
	
	private void sleep_ns(long time_ns)
	{
		try
		{
			long time_ms = (long)(time_ns/1_000_000.0);
			Thread.sleep(time_ms);
		}
		catch(Exception e) {}
	}
	
	
	
	
	public void e() throws Exception
	{
		if(t==null || !t.isAlive())
		{
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}
		else interrupted = true;
	}
}
