package a.entity.gus06.sys.capturescreen1.capture;

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


public class EntityImpl implements Entity, G, MouseListener, MouseMotionListener, KeyListener {

	public String creationDate() {return "20151022";}
	
	public static final long WAIT = 1000;


	private Service getScreenRect;
	private Service autoFront;
	
	private JDialog dialog;
	private JPanel1 panel;
	
	private Rectangle screenRect;
	private Rectangle selectedRect;
	
	private Robot robot;
	private BufferedImage image;
	private BufferedImage result;
	
	private Point start;
	
	


	public EntityImpl() throws Exception
	{
		getScreenRect = Outside.service(this,"gus.x.awt.screen.rect");
		autoFront = Outside.service(this,"gus06.awt.window.autofront");
		
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
		
		autoFront.p(dialog);
	}
	



	public Object g() throws Exception
	{
		start = null;
		selectedRect = null;
		result = null;
		
		try{Thread.sleep(WAIT);}
		catch(InterruptedException e){}
		
		image = robot.createScreenCapture(screenRect);
		panel.requestFocus();
		dialog.setVisible(true);
		
		return result;
	}
	
	
	
	
	private Rectangle getSelectedRect(Point end)
	{
		if(start==null) return null;
		
		int x = Math.min(start.x,end.x);
		int y = Math.min(start.y,end.y);
		int w = Math.abs(start.x-end.x);
		int h = Math.abs(start.y-end.y);
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
	
	
	public void mouseDragged(MouseEvent e)
	{
		Point mouse = e.getLocationOnScreen();
		selectedRect = getSelectedRect(mouse);
		panel.repaint();
	}
	
	
	public void mousePressed(MouseEvent e)
	{
		start = e.getLocationOnScreen();
		selectedRect = null;
	}
	
	
	public void mouseReleased(MouseEvent e)
	{
		Point mouse = e.getLocationOnScreen();
		if(start==null || start.equals(mouse)) return;
		
		selectedRect = getSelectedRect(mouse);
		putResult(buildResult(selectedRect));
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getClickCount()==2) putResult(image);
	}
	


	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) putResult(null);
	}
	
	
	
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	
	
	
	
	private class JPanel1 extends JPanel
	{
		protected void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			
			if(image!=null) g.drawImage(image,0,0,this);
			
			Area area = new Area(screenRect);
			if(selectedRect!=null) area.subtract(new Area(selectedRect));
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f)); 
			g2.setColor(Color.BLACK);
			g2.fill(area);
		}
	}
}
