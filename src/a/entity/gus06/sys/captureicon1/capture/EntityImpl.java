package a.entity.gus06.sys.captureicon1.capture;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JPanel;

import a.framework.*;
import java.awt.Composite;
import java.awt.MouseInfo;


public class EntityImpl implements Entity, G, MouseListener, MouseMotionListener, KeyListener {

	public String creationDate() {return "20201205";}
	
	public static final long WAIT = 1000;
	
	public static final int MAGNIFICATION = 4;
	public static final int GAP = 10;


	private Service getScreenRect;
	private Service autoFront;
	private Service controlMouse;
	
	private JDialog dialog;
	private JPanel1 panel;
	
	private Rectangle screenRect;
	private Rectangle selectedRect;
	
	private Robot robot;
	private BufferedImage image;
	private BufferedImage result;
	
	private int m_x;
	private int m_y;
	
	


	public EntityImpl() throws Exception
	{
		getScreenRect = Outside.service(this,"gus.x.awt.screen.rect");
		autoFront = Outside.service(this,"gus06.awt.window.autofront");
		controlMouse = Outside.service(this,"gus06.mouse.control.withkeys");
		
		screenRect = (Rectangle) getScreenRect.g();
		robot = new Robot();
		
		panel = new JPanel1();
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		panel.addKeyListener(this);
		
		panel.setFocusable(true);
		
		dialog = new JDialog();
		dialog.setModal(true);
		dialog.setContentPane(panel);
		dialog.setBounds(screenRect);
		dialog.setUndecorated(true);
		dialog.setResizable(false);
		dialog.setAlwaysOnTop(true);
		
		controlMouse.p(panel);
		autoFront.p(dialog);
	}
	



	public Object g() throws Exception
	{
		selectedRect = null;
		result = null;
		
		try{Thread.sleep(WAIT);}
		catch(InterruptedException e){}
		
		image = robot.createScreenCapture(screenRect);
		panel.requestFocus();
		dialog.setVisible(true);
		
		return result;
	}
	
	
	
	
	private Rectangle getSelectedRect(Point center)
	{
		int x = center.x-8;
		int y = center.y-8;
		int w = 16;
		int h = 16;
		return new Rectangle(x,y,w,h);
	}
	
	
	
	private void putResult(BufferedImage img)
	{
		result = img;
		dialog.setVisible(false);
	}
	
	private BufferedImage buildResult(Rectangle r)
	{
		if(image==null || r==null) return null;
		return image.getSubimage(r.x,r.y,r.width,r.height);
	}
	
	public void mouseMoved(MouseEvent e)
	{
		Point mouse = e.getLocationOnScreen();
		m_x = mouse.x;
		m_y = mouse.y;
		
		selectedRect = getSelectedRect(mouse);
		panel.repaint();
	}
	
	public void mouseReleased(MouseEvent e)
	{
		Point mouse = e.getLocationOnScreen();
		selectedRect = getSelectedRect(mouse);
		putResult(buildResult(selectedRect));
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) cancel();
		if(e.getKeyCode()==KeyEvent.VK_ENTER) select();
	}
	
	
	
	
	private void cancel()
	{
		putResult(null);
	}
	
	private void select()
	{
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		selectedRect = getSelectedRect(mouse);
		putResult(buildResult(selectedRect));
	}
	
	
	
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	
	
	
	
	private class JPanel1 extends JPanel
	{
		protected void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			
			if(image!=null) g2.drawImage(image,0,0,this);
			
			Area area = new Area(screenRect);
			if(selectedRect!=null) area.subtract(new Area(selectedRect));
			
			Composite composite0 = g2.getComposite();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f)); 
			g2.setColor(Color.BLACK);
			g2.fill(area);
			
			BufferedImage img = buildResult(selectedRect);
			g2.setComposite(composite0);
			
			
			boolean isNearTopLeft = selectedRect.x<100 && selectedRect.y<100;
			
			int dx = MAGNIFICATION * 16;
			int dy = MAGNIFICATION * 16;
			
			int x = GAP;
			int y = isNearTopLeft ? 150 : GAP;
			
			g2.drawImage(img,x,y,dx,dy,this);
		}
	}
}