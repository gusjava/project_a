package a.entity.gus06.swing.comp.cust.removable;

import a.framework.*;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyListener;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.Window;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20221003";}


	public void p(Object obj) throws Exception
	{new Holder((Component) obj);}
	
	
	
	public class Holder implements KeyListener
	{
		private Component comp;
	
		public Holder(Component comp)
		{
			this.comp = comp;
			comp.setFocusable(true);
			comp.addKeyListener(this);
		}
	
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}

		public void keyTyped(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_DELETE) removeFrame();
		}
		
		private void removeFrame()
		{
			Window frame = (Window) SwingUtilities.getRoot(comp);
			if(frame!=null) frame.dispose();
		}
	}
}