package a.entity.gus06.swing.panel.screen.image;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import javax.swing.ImageIcon;


public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140909";}
	
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
		private Exception e;
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(e!=null) {paintError((Graphics2D)g);return;}
			
			if(image==null) return;
        
			if(image instanceof RenderedImage)
				paintRenderedImage((Graphics2D)g,(RenderedImage)image);
			else if(image instanceof Image)
				paintImage((Graphics2D)g,(Image)image);
			else if(image instanceof ImageIcon)
				paintImageIcon((Graphics2D)g,(ImageIcon)image);
			else if(image instanceof G)
				paintG((Graphics2D)g,(G)image);
		}
		
		private void paintRenderedImage(Graphics2D g2, RenderedImage img)
		{
			int imageW = img.getWidth();
			int imageH = img.getHeight();
			if(imageH<=0) return;
		
			Insets ins = getInsets();
		
        		double cx = (double)(getWidth()-ins.left-ins.right)/(double)imageW;
    			double cy = (double)(getHeight()-ins.bottom-ins.top)/(double)imageH;
    		
    			if(cx>=cy)
    			{
    				int a = (int)((getWidth()-ins.left-ins.right-imageW*cy)/2);
    				AffineTransform af = AffineTransform.getTranslateInstance(a+ins.left,ins.top);
    	        		af.scale(cy,cy);
    	       		 	g2.drawRenderedImage(img,af);
			}
			else
    			{
    				int a = (int)((getHeight()-ins.bottom-ins.top-imageH*cx)/2);
    				AffineTransform af = AffineTransform.getTranslateInstance(ins.left,a+ins.top);
    				af.scale(cx,cx);
				g2.drawRenderedImage(img,af);
			}
		}
		
		
		private void paintImage(Graphics2D g2, Image img)
		{
			int imageW = img.getWidth(null);
			int imageH = img.getHeight(null);
			if(imageH<=0) return;
		
			Insets ins = getInsets();
		
			double cx = (double)(getWidth()-ins.left-ins.right)/(double)imageW;
    			double cy = (double)(getHeight()-ins.bottom-ins.top)/(double)imageH;

			if(cx>=cy)
			{
				int a = (int)((getWidth()-ins.left-ins.right-imageW*cy)/2);
				int dx = (int)(imageW*cy);
				int dy = getHeight()-ins.bottom-ins.top;
				g2.drawImage(img,a+ins.left,ins.top,dx,dy,this);
			}
			else
			{
				int a = (int)((getHeight()-ins.bottom-ins.top-imageH*cx)/2);	
				int dx = getWidth()-ins.left-ins.right;
				int dy = (int)(imageH*cx);
				g2.drawImage(img,ins.left,a+ins.top,dx,dy,this);
			}
		}
		
		
		private void paintImageIcon(Graphics2D g2, ImageIcon img)
		{paintImage(g2,img.getImage());}
		
		
		private void paintG(Graphics2D g2, G g)
		{
			try
			{
				Object img = g.g();
				if(img==null) return;
				
				if(img instanceof RenderedImage)
					paintRenderedImage(g2,(RenderedImage)img);
				else if(img instanceof Image)
					paintImage(g2,(Image)img);
				else if(img instanceof ImageIcon)
					paintImageIcon(g2,(ImageIcon)img);
			}
			catch(Exception e)
			{
				this.e = e;
				paintError(g2);
			}
		}
		
		private void paintError(Graphics2D g2)
		{
			g2.setColor(Color.RED);
			g2.drawString(e.toString(), 10, 20);
		}
	}
}