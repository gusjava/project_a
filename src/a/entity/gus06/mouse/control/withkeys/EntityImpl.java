package a.entity.gus06.mouse.control.withkeys;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.Robot;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201213";}


	private Robot robot;

	public EntityImpl() throws Exception
	{
		robot = new Robot();
	}
	
	
	public void p(Object obj) throws Exception
	{
		new Holder((JComponent) obj);
	}
	
	
	private class Holder implements KeyListener, MouseListener
	{
		private JComponent comp;
		
		public Holder(JComponent comp)
		{
			this.comp = comp;
			
			comp.setFocusable(true);
			comp.addKeyListener(this);
			comp.addMouseListener(this);
		}
		
		public void mouseExited(MouseEvent evt) {}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseReleased(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {}
		public void mousePressed(MouseEvent evt)
		{comp.requestFocusInWindow();}
		
		public void keyReleased(KeyEvent evt){}
		public void keyTyped(KeyEvent evt){}
		public void keyPressed(KeyEvent evt)
		{
			if(evt.getKeyCode()==KeyEvent.VK_UP) move(0,-1);
			if(evt.getKeyCode()==KeyEvent.VK_DOWN) move(0,1);
			if(evt.getKeyCode()==KeyEvent.VK_RIGHT) move(1,0);
			if(evt.getKeyCode()==KeyEvent.VK_LEFT) move(-1,0);
		}
		
		private void move(int dx, int dy)
		{
			Point p = MouseInfo.getPointerInfo().getLocation();
			robot.mouseMove(p.x+dx,p.y+dy);
			comp.repaint();
		}
	}
}