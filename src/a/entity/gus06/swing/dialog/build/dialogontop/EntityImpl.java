package a.entity.gus06.swing.dialog.build.dialogontop;

import a.framework.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20140915";}


	private Service toComp;
	private Service getTimer;
	private Timer timer;

	public EntityImpl() throws Exception
	{
		toComp = Outside.service(this,"gus06.swing.comp.find");
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
    		Dialog1 d = new Dialog1((JComponent) toComp.t(obj));
		d.setVisible(true);
	}
	
	
	public Object t(Object obj) throws Exception
	{
    		return new Dialog1((JComponent) toComp.t(obj));
	}
	
	
	
	
	
	private class Dialog1 extends JDialog
	{
		public Dialog1(JComponent content)
		{
			super((JFrame)null,false);
			
			setUndecorated(true);
			setResizable(false);
			setAlwaysOnTop(true);
			
			setContentPane(content);
			setLocationRelativeTo(null);
			
			TimerTask task = new TimerTask(){public void run(){updateGui();}};
	    		timer.schedule(task,new Date(),100);
		}
		
		private void updateGui()
		{
			pack();
//			toFront();
		}
	}
}
