package a.entity.gus06.awt.fullscreen.onkey.space;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}


	private Service fullScreen;

	public EntityImpl() throws Exception
	{
		fullScreen = Outside.service(this,"gus06.awt.fullscreen");
	}


	public void p(Object obj) throws Exception
	{new JComponentHolder((Component)obj);}

	
	
	private class JComponentHolder implements KeyListener, MouseListener
	{
		private Component comp;
		private boolean isFullScreen = false;
		
		public JComponentHolder(Component comp)
		{
			this.comp = comp;
			comp.setFocusable(true);
			comp.addKeyListener(this);
			comp.addMouseListener(this);
			
			if(comp instanceof Container)
			{
				Container c = (Container)comp;
				int n = c.getComponentCount();
				for(int i=0;i<n;i++)
					new JComponentHolder(c.getComponent(i));
			}
		}

		public void keyPressed(KeyEvent e) {if(e.getKeyCode()==KeyEvent.VK_SPACE) onKeySpace();}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}

		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {comp.requestFocusInWindow();}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		
		private void onKeySpace()
		{
			if(!isFullScreen)startsFullScreen(comp);
			else stopsFullScreen();
			isFullScreen = !isFullScreen;
		}
	}
	
	
	private void startsFullScreen(Component comp)
	{
		try{fullScreen.p(comp);}
		catch(Exception e)
		{Outside.err(this,"startsFullScreen(Component)",e);}
	}
	
	private void stopsFullScreen()
	{
		try{fullScreen.p(null);}
		catch(Exception e)
		{Outside.err(this,"stopsFullScreen()",e);}
	}
}
