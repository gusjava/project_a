package a.entity.gus06.swing.label.build.watcher.g;

import a.framework.*;
import java.util.Timer;
import java.util.Date;
import javax.swing.JLabel;
import java.util.TimerTask;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190625";}

	public static final long LAPSE = 200;

	
	
	private Service getTimer;
	private Timer timer;
	

	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	public Object t(Object obj) throws Exception
	{return new JLabel1((G) obj);}
	
	
	private void updateLabel(JLabel label, G g)
	{
		try
		{
			String display = (String) g.g();
			label.setText(display);
		}
		catch(Exception e)
		{Outside.err(this,"updateLabel(JLabel,G)",e);}
	}

	
	
	
	private class JLabel1 extends JLabel
	{
		private G g;
		private TimerTask task;
		
		public JLabel1(G g)
		{
			super(" ");
			this.g = g;
			updateLabel(this,g);
			
			task = new TimerTask() {public void run() {update_();}};
			timer.schedule(task,new Date(),LAPSE);
		}
		
		private synchronized void update_()
		{updateLabel(this,g);}
	}
}
