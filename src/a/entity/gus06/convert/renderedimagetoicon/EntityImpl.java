package a.entity.gus06.convert.renderedimagetoicon;

import a.framework.*;

import javax.swing.Icon;
import java.awt.Graphics;
import java.awt.image.RenderedImage;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250228";}

	public Object t(Object obj) throws Exception
	{return new Icon1((RenderedImage) obj);}
	
	
	public class Icon1 implements Icon
	{
		private RenderedImage img;
		public Icon1(RenderedImage img){this.img = img;}
	
		public int getIconWidth() {return img.getWidth();}
		public int getIconHeight() {return img.getHeight();}
		
		public void paintIcon(Component c, Graphics g, int x, int y)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.drawRenderedImage(img, AffineTransform.getTranslateInstance(0,0));
		}
	}
}
