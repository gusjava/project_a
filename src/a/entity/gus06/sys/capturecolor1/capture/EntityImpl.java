package a.entity.gus06.sys.capturecolor1.capture;

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
import java.awt.Font;


public class EntityImpl implements Entity, G, MouseListener, MouseMotionListener, KeyListener {

	public String creationDate() {return "20201213";}
	
	public static final long WAIT = 1000;
	
	public static final int GAP = 10;
	public static final int LENGTH = 5;
	
	public static final Font FONT11 = new Font("Serif",Font.BOLD,11);


	private Service getScreenRect;
	private Service autoFront;
	
	private JDialog dialog;
	private JPanel1 panel;
	
	private Rectangle screenRect;
	private Color selectedColor;
	
	private Robot robot;
	private BufferedImage image;
	
	private int m_x;
	private int m_y;
	
	


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
		selectedColor = null;
		
		try{Thread.sleep(WAIT);}
		catch(InterruptedException e){}
		
		image = robot.createScreenCapture(screenRect);
		panel.requestFocus();
		dialog.setVisible(true);
		
		return selectedColor;
	}
	
	
	
	
	private Rectangle getSelectedRect()
	{
		int x = m_x-8;
		int y = m_y-8;
		int w = 16;
		int h = 16;
		return new Rectangle(x,y,w,h);
	}
	
	private Rectangle getZoom()
	{
		int x = m_x-2;
		int y = m_y-2;
		int w = 5;
		int h = 5;
		return new Rectangle(x,y,w,h);
	}
	
	private Color getColor()
	{
		return robot.getPixelColor(m_x, m_y);
	}
	
	private BufferedImage subImage(Rectangle r)
	{
		if(image==null || r==null) return null;
		return image.getSubimage(r.x,r.y,r.width,r.height);
	}
	
	
	
	
	public void mouseMoved(MouseEvent e)
	{
		Point mouse = e.getLocationOnScreen();
		m_x = mouse.x;
		m_y = mouse.y;
		
		panel.repaint();
	}
	
	public void mouseReleased(MouseEvent e)
	{
		Point mouse = e.getLocationOnScreen();
		m_x = mouse.x;
		m_y = mouse.y;
		
		select();
	}
	

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) cancel();
		else if(e.getKeyCode()==KeyEvent.VK_ENTER) select();
		else if(e.getKeyCode()==KeyEvent.VK_UP) move(0,-1);
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) move(0,1);
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) move(1,0);
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) move(-1,0);
	}
	
	
	
	
	private void cancel()
	{
		selectedColor = null;
		dialog.setVisible(false);
	}
	
	private void select()
	{
		selectedColor = getColor();
		dialog.setVisible(false);
	}
	
	private void move(int dx, int dy)
	{
		m_x += dx;
		m_y += dy;
		panel.repaint();
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
			
			Rectangle selectedRect = getSelectedRect();
			Rectangle zoom = getZoom();
			Color color = getColor();
			
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();
			
			Color textColor = red+green+blue<384 ? Color.WHITE : Color.BLACK;
			
			area.subtract(new Area(selectedRect));
			
			Composite composite0 = g2.getComposite();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f)); 
			g2.setColor(Color.BLACK);
			g2.fill(area);
			
			g2.setComposite(composite0);
			
			
			boolean isNearTopLeft = selectedRect.x<230 && selectedRect.y<150;
			
			int dx = 16 * 5;
			int dy = 16 * 5;
			
			int x = GAP;
			int y = isNearTopLeft ? 200 : GAP;
			
			int x1 = x+dx;
			int dx1 = dx+30;
			
			// PART 1
			
			BufferedImage zoomImg = subImage(zoom);
			g2.drawImage(zoomImg,x,y,dx,dy,this);
			
			g2.setColor(textColor);
			g2.drawRect(
				x+2*16,
				y+2*16,
				16,
				16);
			
			
			// PART 2
			
			g2.fillRect(x1,y,dx1,dy);
			
			g2.setColor(color);
			g2.fillRect(x1+2,y+2,dx1-4,dy-4);
			
			g2.setColor(textColor);
			g2.setFont(FONT11);
			
			int r = y+20;
			
			g2.drawString("Pixel:", 		x1+5, r);
			g2.drawString("["+m_x+","+m_y+"]", 	x1+45, r);
			
			r += 20;
			
			g2.drawString("Red:", 			x1+5, r);
			g2.drawString(""+color.getRed(), 	x1+45, r);
			
			r += 13;
			
			g2.drawString("Green:",			x1+5, r);
			g2.drawString(""+color.getGreen(), 	x1+45, r);
			
			r += 13;
			
			g2.drawString("Blue:", 			x1+5, r);
			g2.drawString(""+color.getBlue(), 	x1+45, r);
		}
	}
}