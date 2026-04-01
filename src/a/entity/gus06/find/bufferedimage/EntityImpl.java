package a.entity.gus06.find.bufferedimage;

import a.framework.*;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import javax.media.jai.PlanarImage;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140918";}


	private Service createImage;
	private Service baToImage;
	private Service iconToImage;
	private Service fileToImage;

	
	public EntityImpl() throws Exception
	{
		createImage = Outside.service(this,"gus06.awt.bufferedimage.create");
		baToImage = Outside.service(this,"gus06.convert.bytearraytobufferedimage");
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
		fileToImage = Outside.service(this,"gus06.file.read.image.imageio");
	}

	
	
	public Object t(Object obj) throws Exception
	{return toBufferedImage(obj);}
	
	
	
	private BufferedImage toBufferedImage(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof BufferedImage) return (BufferedImage) obj;
		if(obj instanceof RenderedImage) return toBufferedImage3((RenderedImage) obj);
		if(obj instanceof Image) return toBufferedImage((Image) obj);
		if(obj instanceof Icon) return toBufferedImage((Image) iconToImage.t(obj));
		if(obj instanceof byte[]) return toBufferedImage((Image) baToImage.t(obj));
		if(obj instanceof File) return toBufferedImage((Image) fileToImage.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private BufferedImage toBufferedImage(Image image) throws Exception
	{
		if(image instanceof BufferedImage) return (BufferedImage) image;
		
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		BufferedImage buffImg = createImage(w,h);
		Graphics2D g = buffImg.createGraphics();
		g.drawImage(image,0,0,null);
		g.dispose();
		return buffImg;
	}
	
	private BufferedImage toBufferedImage(RenderedImage image) throws Exception
	{
		if(image instanceof BufferedImage) return (BufferedImage) image;
		
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage buffImg = createImage(w,h);
		Graphics2D g = buffImg.createGraphics();
		g.drawRenderedImage(image,AffineTransform.getTranslateInstance(0,0));
		g.dispose();
		return buffImg;
	}
	
	
	
	private BufferedImage createImage(int w, int h) throws Exception
	{return (BufferedImage) createImage.t(new int[]{w,h});}
	
	
	
	
	
	public BufferedImage toBufferedImage2(RenderedImage img)
	{
	    if(img instanceof BufferedImage) return (BufferedImage) img;  
	      
	    ColorModel cm = img.getColorModel();
	    int width = img.getWidth();
	    int height = img.getHeight();
	    WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
	    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
	    Hashtable properties = new Hashtable();
	    String[] keys = img.getPropertyNames();
	    if (keys!=null) {
	        for (int i = 0; i < keys.length; i++) {
	            properties.put(keys[i], img.getProperty(keys[i]));
	        }
	    }
	    BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
	    img.copyData(raster);
	    return result;
	}
	
	private BufferedImage toBufferedImage3(RenderedImage img)
	{
		return PlanarImage.wrapRenderedImage(img).getAsBufferedImage();
	}
}
