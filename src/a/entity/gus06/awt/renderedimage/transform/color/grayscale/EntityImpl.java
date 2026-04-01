package a.entity.gus06.awt.renderedimage.transform.color.grayscale;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151004";}

	

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Image)
		{
			Image image = (Image) obj;
			BufferedImage buffImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);  
			Graphics2D g = buffImage.createGraphics();  
			g.drawImage(image,0,0,null);
			g.dispose();
			return buffImage;
		}
		if(obj instanceof RenderedImage)
		{
			RenderedImage image = (RenderedImage) obj;
			BufferedImage buffImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);  
			Graphics2D g = buffImage.createGraphics();  
			g.drawRenderedImage(image,AffineTransform.getTranslateInstance(0,0));
			g.dispose();
			return buffImage;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
