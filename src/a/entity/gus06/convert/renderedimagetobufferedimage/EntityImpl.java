package a.entity.gus06.convert.renderedimagetobufferedimage;

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


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150930";}


	private Service createImage;
	
	public EntityImpl() throws Exception
	{createImage = Outside.service(this,"gus06.awt.bufferedimage.create");}

	
	public Object t(Object obj) throws Exception
	{return toBufferedImage((RenderedImage) obj);}
	
	
	private BufferedImage toBufferedImage(RenderedImage image) throws Exception
	{
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
}
