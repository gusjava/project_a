package a.entity.gus06.find.renderedimage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import a.framework.*;
import java.awt.Graphics2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150929";}
	
	
	
	public EntityImpl() throws Exception
	{
		
	}



	public Object t(Object obj) throws Exception
	{
		if(obj instanceof BufferedImage) return obj;
		if(obj instanceof RenderedImage) return obj;
		if(obj instanceof Image) return toBufferedImage((Image) obj);
		
		// autres conversions ??
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private BufferedImage toBufferedImage(Image image)
	{
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		BufferedImage buffImg = createImage_rgb(w,h);
		Graphics2D g = buffImg.createGraphics();
		g.drawImage(image,0,0,null);
		g.dispose();
		return buffImg;
	}
	
	private BufferedImage createImage_rgb(int w, int h)
	{
		return new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	}
}
