package a.entity.gus06.swing.label.perform.quickstate1;

import a.framework.*;
import javax.swing.JLabel;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151017";}
	
	public static final long LAPSE = 250;
	public static final Color COLOR = new Color(153,204,255);


	private Service getTimer;
	private Timer timer;

	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		Holder holder = new Holder((JLabel) obj);
		timer.schedule(holder,LAPSE);
	}
	
	
	private class Holder extends TimerTask
	{
		private JLabel label;
		private Color background;
		
		public Holder(JLabel label)
		{
			this.label = label;
			background = label.getBackground();
			
			label.setOpaque(true);
			label.setBackground(COLOR);
		}
		
		public void run()
		{label.setBackground(background);}
	}
}
