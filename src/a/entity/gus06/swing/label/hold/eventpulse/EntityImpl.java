package a.entity.gus06.swing.label.hold.eventpulse;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;

public class EntityImpl implements Entity, I, E, Runnable {

	public String creationDate() {return "20190505";}
	
	
	private JLabel label;
	private Thread t;

	public EntityImpl() throws Exception
	{
		label = new JLabel(" ");
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	public void e() throws Exception
	{
		if(t!=null && t.isAlive()) return;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	public void run()
	{
		for(int i=255;i>0;i--)
		{
			Color c = new Color(i,i,i);
			label.setBackground(c);
			
			try{Thread.sleep(3);}
			catch(InterruptedException e){}
		}	
	}
}
