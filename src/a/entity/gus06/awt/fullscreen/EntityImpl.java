package a.entity.gus06.awt.fullscreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, KeyListener, WindowStateListener {

	public String creationDate() {return "20151015";}


	private Service fullScreenEngine;
	private Service focusKeyListener;
	private Service replaceComp;
	
	private boolean isFullScreen = false;
	


	public EntityImpl() throws Exception
	{
		fullScreenEngine = Outside.service(this,"gus06.awt.fullscreen.engine");
		replaceComp = Outside.service(this,"gus06.awt.fullscreen.replacecomp");
		focusKeyListener = Outside.service(this,"gus06.awt.focus.keylistener");
		
		JFrame frame = (JFrame) fullScreenEngine.r("frame");
		frame.addWindowStateListener(this);
	}



	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			if(!isFullScreen) return;
			stopFullScreen();
			return;
		}
		if(obj instanceof JComponent)
		{
			if(isFullScreen) return;
			startFullScreen((JComponent) obj);
			return;
		}
		throw new Exception("Invalid data: "+obj.getClass().getName());
	}


	
	
	private void startFullScreen(JComponent component)
	{
		try
		{
		  replaceComp.p(component);
		  
		  if(component instanceof Scrollable)
			  fullScreenEngine.p(new JScrollPane(component)); 
		  else fullScreenEngine.p(component);
		  
		  focusKeyListener.v("add",this);
		  component.setFocusable(true);
		  component.requestFocusInWindow();
		  
		  isFullScreen = true;
		  send(this,"startFullScreen(JComponent)");
		}
		catch(Exception e)
		{Outside.err(this,"startFullScreen(JComponent)",e);}
	}
	
	
	
	private void stopFullScreen()
	{
		try
		{
			fullScreenEngine.p(null);
			
			focusKeyListener.v("remove",this);
			replaceComp.e();
			
			isFullScreen = false;
			send(this,"stopFullScreen()");
		}
		catch(Exception e)
		{Outside.err(this,"stopFullScreen()",e);}
	}
	
  
	
	
	public void keyTyped(KeyEvent e){}	
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			stopFullScreen();
	}
	
	public void windowStateChanged(WindowEvent e)
	{stopFullScreen();}
}
