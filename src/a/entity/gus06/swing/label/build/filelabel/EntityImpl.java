package a.entity.gus06.swing.label.build.filelabel;

import a.framework.*;
import java.util.Timer;
import java.io.File;
import java.util.Date;
import javax.swing.JLabel;
import java.util.TimerTask;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141209";}

	public static final long LAPSE = 1000;


	private Service labelCust1;
	private Service labelCust2;
	private Service initCopy;
	
	private Service getTimer;
	private Timer timer;
	

	public EntityImpl() throws Exception
	{
		labelCust1 = Outside.service(this,"gus06.swing.label.cust3.filedisplay");
		labelCust2 = Outside.service(this,"gus06.swing.label.cust3.filedisplay2");
		initCopy = Outside.service(this,"gus06.swing.comp.cust3.filecopy");
		
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		JLabel1 label = new JLabel1((File) obj);
		initCopy.p(new Object[]{label,label});
		return label;
	}
	
	
	
	private void updateLabel(Service s, JLabel label, File file)
	{
		try{s.p(new Object[]{label,file});}
		catch(Exception e)
		{Outside.err(this,"updateLabel(Service,JLabel,File)",e);}
	}

	
	
	
	private class JLabel1 extends JLabel implements P, G
	{
		private File file;
		private TimerTask task;
		
		public JLabel1(File file)
		{
			super(" ");
			this.file = file;
			updateLabel(labelCust1,this,file);
			
			task = new TimerTask() {public void run() {update_();}};
			timer.schedule(task,new Date(),LAPSE);
		}
		
		
		public Object g() throws Exception
		{return file;}
		
		
		public void p(Object obj) throws Exception
		{
			file = (File) obj;
			updateLabel(labelCust1,this,file);
		}
		
		
		private void update_()
		{updateLabel(labelCust2,this,file);}
	}
}
