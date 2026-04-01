package a.entity.gus06.sys.popup1.gui.controller;

import a.framework.*;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.sun.jna.platform.WindowUtils;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161006";}
	
	public static final long DELAY = 4000;
	public static final float DELTA = 0.01f;


	private Service mouseInside;
	private Service timer;
	

	public EntityImpl() throws Exception
	{
		mouseInside = Outside.service(this,"gus06.awt.window.filter.mouseinside");
		timer = Outside.service(this,"gus06.time.timer.ms10");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Controller((JDialog) obj);}
	
	
	
	private class Controller extends S1 implements E, V, ActionListener
	{
		private JDialog dialog;
		private F retainer;
		private float alpha;
		private long t0;
		
		
		public Controller(JDialog dialog) throws Exception
		{
			this.dialog = dialog;
			alpha = 1.0f;
			t0 = -1;
			timer.addActionListener(this);
		}
		
		
		public void actionPerformed(ActionEvent e)
		{update();}
		
		
		public void e() throws Exception
		{reset();}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("retainer")) {retainer = (F) obj;return;}
			throw new Exception("Unknown key: "+key);
		}
		
		
		private synchronized void reset()
		{
			t0 = System.currentTimeMillis();
		}
		
		private boolean isRetained()
		{
			try{return retainer!=null && retainer.f(null);}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"isRetained()",e);}
			return false;
		}
		
		
		private synchronized void update()
		{
			
			long dt = System.currentTimeMillis() - t0;
			if(dt<DELAY || mouseInside() || isRetained())
			{
				if(!dialog.isVisible())
				{
					alpha = 0.0f;
					System.setProperty("sun.java2d.noddraw","true");
					WindowUtils.setWindowAlpha(dialog,alpha);
					dialog.setVisible(true);
				}
				else if(alpha<1.0f)
				{
					alpha += DELTA;
					System.setProperty("sun.java2d.noddraw","true");
					WindowUtils.setWindowAlpha(dialog,alpha);
				}
			}
			else if(dialog.isVisible())
			{
				if(alpha>0.0f)
				{
					alpha -= DELTA;
					System.setProperty("sun.java2d.noddraw","true");
					WindowUtils.setWindowAlpha(dialog,alpha);
				}
				else
				{
					dialog.setVisible(false);
					closed();
				}
			}
		}
		
		
		private boolean mouseInside()
		{
			try{return mouseInside.f(dialog);}
			catch(Exception e)
			{
				Outside.err(EntityImpl.this,"mouseInside()",e);
				try{timer.removeActionListener(this);}catch(Exception e1) {}
			}
			return false;
		}
		
		
		private void closed()
		{send(this,"closed()");}
	}
}