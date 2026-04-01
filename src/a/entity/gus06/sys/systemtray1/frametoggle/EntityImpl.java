package a.entity.gus06.sys.systemtray1.frametoggle;

import a.framework.*;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JFrame;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160422";}
	
	public static final String KEY_FRAME_ONMINIMIZE_HIDE = "app.frame.onminimize.hide";
	public static final String KEY_TRAYICON_ONCLICK_SHOW = "app.trayicon.onclick.show";
	public static final String KEY_TRAYICON_ONCLICK_HIDE = "app.trayicon.onclick.hide";


	private Service propBoolDF;
	
	public EntityImpl() throws Exception
	{
		propBoolDF = Outside.service(this,"propbool_df");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		TrayIcon tray = (TrayIcon) o[0];
		JFrame frame = (JFrame) o[1];
		
		new Holder(tray,frame);
	}
	
	
	private boolean propBool(String key)
	{
		try{return propBoolDF.f(key);}
		catch(Exception e){Outside.err(this,"propBool(String)",e);}
		return false;
	}

	
	
	
	
	private class Holder implements MouseListener, WindowStateListener
	{
		private TrayIcon tray;
		private JFrame frame;
		private int state;
		
		public Holder(TrayIcon tray, JFrame frame)
		{
			this.tray = tray;
			this.frame = frame;
			state = frame.getExtendedState();
			
			tray.addMouseListener(this);
			frame.addWindowStateListener(this);
		}
	
		public void windowStateChanged(WindowEvent e)
		{
			if(e.getNewState()==1 || e.getNewState()==7)
			if(propBool(KEY_FRAME_ONMINIMIZE_HIDE))
			{
				frame.setVisible(false);
				state = e.getOldState();
			}
		}
	
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseClicked(MouseEvent e)
		{
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				if(!frame.isVisible())
				{
					if(propBool(KEY_TRAYICON_ONCLICK_SHOW))
					activate();
				}
				else
				{
					if(propBool(KEY_TRAYICON_ONCLICK_HIDE))
					disactivate();
				}
			}
		}

		private void activate()
		{
			if(state==JFrame.ICONIFIED) state = JFrame.MAXIMIZED_BOTH;
			
			frame.setVisible(true);
			frame.setExtendedState(state);
			frame.toFront();
		}
		
		private void disactivate()
		{
			state = frame.getExtendedState();
			frame.setVisible(false);
		}
	}
}
