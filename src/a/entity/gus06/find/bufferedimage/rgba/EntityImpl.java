package a.entity.gus06.find.bufferedimage.rgba;

import a.framework.*;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250308";}

	private Service baToImage;
	private Service iconToImage;

	public EntityImpl() throws Exception
	{
		baToImage = Outside.service(this,"gus06.convert.bytearraytobufferedimage");
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
	}

	
	public Object t(Object obj) throws Exception
	{return toBufferedImage(obj);}
	
	
	
	private BufferedImage toBufferedImage(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof BufferedImage) 	return toBufferedImage((BufferedImage) obj);
		if(obj instanceof RenderedImage) 	return toBufferedImage((RenderedImage) obj);
		if(obj instanceof Image) 		return toBufferedImage((Image) obj);
		if(obj instanceof Icon) 		return toBufferedImage((Image) iconToImage.t(obj));
		if(obj instanceof byte[]) 		return toBufferedImage((Image) baToImage.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private BufferedImage toBufferedImage(BufferedImage img)
	{
		if(img.getType()==BufferedImage.TYPE_INT_ARGB) return img;
		
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    		Graphics2D g = newImage.createGraphics();
    		g.drawImage(img, 0, 0, null);
    		g.dispose();
    		return newImage;
	}
	
	
	private BufferedImage toBufferedImage(Image img) throws Exception
	{
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(img,0,0,null);
		g.dispose();
		return newImage;
	}
	
	private BufferedImage toBufferedImage(RenderedImage img) throws Exception
	{
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawRenderedImage(img, AffineTransform.getTranslateInstance(0,0));
		g.dispose();
		return newImage;
	}
}