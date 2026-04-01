package a.entity.gus06.swing.panel.screen.image.north;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import javax.swing.ImageIcon;


public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20191215";}
	
	private Service focusOnClicked;

	private ScreenJPanel screen;
	private Object image;
	
	public EntityImpl() throws Exception
	{
		focusOnClicked = Outside.service(this,"gus06.swing.comp.cust.focusonclicked");
		
		screen = new ScreenJPanel();
		focusOnClicked.p(screen);
	}
	
	
	public Object g() throws Exception
	{return image;}
	
	
	public Object i() throws Exception
	{return screen;}
	
	
	public void p(Object obj) throws Exception
	{
		image = obj;
		screen.repaint();
	}
	
	
	
	
	public class ScreenJPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(image==null) return;
        
			if(image instanceof RenderedImage)
				paintRenderedImage((Graphics2D)g,(RenderedImage)image);
			else if(image instanceof Image)
				paintImage((Graphics2D)g,(Image)image);
			else if(image instanceof ImageIcon)
				paintImageIcon((Graphics2D)g,(ImageIcon)image);
		}
		
		private void paintRenderedImage(Graphics2D g2, RenderedImage image)
		{
			int imageW = image.getWidth();
			int imageH = image.getHeight();
			if(imageH<=0) return;
		
			Insets ins = getInsets();
		
        		double cx = (double)(getWidth()-ins.left-ins.right)/(double)imageW;
    			double cy = (double)(getHeight()-ins.bottom-ins.top)/(double)imageH;
    		
    			if(cx>=cy)
    			{
    				int a = (int)((getWidth()-ins.left-ins.right-imageW*cy)/2);
    				AffineTransform af = AffineTransform.getTranslateInstance(a+ins.left,ins.top);
    	        		af.scale(cy,cy);
    	       		 	g2.drawRenderedImage(image,af);
			}
			else
    			{
    				AffineTransform af = AffineTransform.getTranslateInstance(ins.left,ins.top);
    				af.scale(cx,cx);
				g2.drawRenderedImage(image,af);
			}
		}
		
		
		private void paintImage(Graphics2D g2, Image image)
		{
			int imageW = image.getWidth(null);
			int imageH = image.getHeight(null);
			if(imageH<=0) return;
		
			Insets ins = getInsets();
		
			double cx = (double)(getWidth()-ins.left-ins.right)/(double)imageW;
    			double cy = (double)(getHeight()-ins.bottom-ins.top)/(double)imageH;

			if(cx>=cy)
			{
				int a = (int)((getWidth()-ins.left-ins.right-imageW*cy)/2);
				int dx = (int)(imageW*cy);
				int dy = getHeight()-ins.bottom-ins.top;
				g2.drawImage(image,a+ins.left,ins.top,dx,dy,this);
			}
			else
			{
				int dx = getWidth()-ins.left-ins.right;
				int dy = (int)(imageH*cx);
				g2.drawImage(image,ins.left,ins.top,dx,dy,this);
			}
		}
		
		private void paintImageIcon(Graphics2D g2, ImageIcon image)
		{paintImage(g2,image.getImage());}
	}
}
