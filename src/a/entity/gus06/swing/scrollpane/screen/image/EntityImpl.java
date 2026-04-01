package a.entity.gus06.swing.scrollpane.screen.image;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;


public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20191116";}
	
	private Service focusOnClicked;

	private ScreenJPanel screen;
	private JScrollPane scrollPane;
	private Object image;
	
	public EntityImpl() throws Exception
	{
		focusOnClicked = Outside.service(this,"gus06.swing.comp.cust.focusonclicked");
		
		screen = new ScreenJPanel();
		scrollPane = new JScrollPane(screen);
		
		focusOnClicked.p(screen);
	}
	
	
	public Object g() throws Exception
	{return image;}
	
	
	public Object i() throws Exception
	{return scrollPane;}
	
	
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
		
		
		public Dimension getPreferredSize()
		{
			if(image instanceof RenderedImage) return getPreferredSizeFrom((RenderedImage) image);
			if(image instanceof ImageIcon) return getPreferredSizeFrom((ImageIcon) image);
			if(image instanceof Image) return getPreferredSizeFrom((Image) image);
			
			return super.getPreferredSize();
		}
		
		private Dimension getPreferredSizeFrom(RenderedImage image)
		{return new Dimension(image.getWidth(),image.getHeight());}
		
		private Dimension getPreferredSizeFrom(Image image)
		{return new Dimension(image.getWidth(null),image.getHeight(null));}
		
		private Dimension getPreferredSizeFrom(ImageIcon image)
		{return getPreferredSizeFrom(image.getImage());}
		
		
		
		
		
		private void paintRenderedImage(Graphics2D g2, RenderedImage image)
		{
			int imageW = image.getWidth();
			int imageH = image.getHeight();
			if(imageH<=0) return;
			
			g2.drawRenderedImage(image,null);
		}
		
		private void paintImage(Graphics2D g2, Image image)
		{
			int imageW = image.getWidth(null);
			int imageH = image.getHeight(null);
			if(imageH<=0) return;
			
			g2.drawImage(image,0,0,imageW,imageH,this);
		}
		
		private void paintImageIcon(Graphics2D g2, ImageIcon image)
		{paintImage(g2,image.getImage());}
	}
}
