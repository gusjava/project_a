package a.entity.gus06.convert.imagetojtooltip;

import a.framework.*;
import javax.swing.JToolTip;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220611";}
	
	public Object t(Object obj) throws Exception
	{
		return new JToolTipImage((Image) obj);
	}
	
	
	private class JToolTipImage extends JToolTip
	{
		private Image image;
		
		public JToolTipImage(Image image)
		{
			super();
			this.image = image;
		}
		
		protected void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image,0,0,getWidth(),getHeight(),this);
		}
		
		public int getWidth()
		{return image.getWidth(null);}
		
		public int getHeight()
		{return image.getHeight(null);}
		
		public Dimension getPreferredSize()
		{return new Dimension(getWidth(), getHeight());}
		
		public Dimension getSize()
		{return new Dimension(getWidth(), getHeight());}
	}
}
