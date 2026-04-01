package a.entity.gus06.awt.component.paint.behindframe;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160421";}

	public static final long LAPSE = 2000;
	

	private Service behindFrame;
	private Timer timer;
	

	public EntityImpl() throws Exception
	{
		behindFrame = Outside.service(this,"gus06.image.printcomponent.behindframe");
		timer = new Timer("TIMER_"+getClass().getName());
	}


	public Object t(Object obj) throws Exception
	{return new PaintHandler((Component) obj);}
	
	
	
	private BufferedImage imageBehind(Component comp)
	{
		try{return (BufferedImage) behindFrame.t(comp);}
		catch(Exception e){Outside.err(this,"imageBehind(Component)",e);}
		return null;
	}
	
	
	
	private class PaintHandler implements P
	{
		private Component comp;
		private BufferedImage image;
		
		public PaintHandler(Component comp) throws Exception
		{
			this.comp = comp;
			
			TimerTask task = new TimerTask() {public void run() {refresh();}};
			timer.schedule(task,new Date(),LAPSE);
		}
		
		private void refresh()
		{
			image = imageBehind(comp);
			comp.repaint();
		}
		
		public void p(Object obj) throws Exception
		{
			Graphics2D g2 = (Graphics2D) obj;
			if(image!=null) g2.drawImage(image,0,0,image.getWidth(comp),image.getHeight(comp),comp);
		}
	}
}
