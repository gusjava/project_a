package a.entity.gus06.awt.bufferedimage.print.icon.se10;

import a.framework.*;
import javax.swing.Icon;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201018";}
	
	public static final double H_FACTOR = 0.1;


	private Service iconToImage;

	public EntityImpl() throws Exception
	{
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage image = (BufferedImage) o[0];
		Icon icon = (Icon) o[1];
		
		if(image==null) return null;
		if(icon==null) return image;
		
		Image iconImg = (Image) iconToImage.t(icon);
		Graphics2D g = image.createGraphics();
		
		double w = image.getWidth();
		double h = image.getHeight();
		
		double w0 = iconImg.getWidth(null);
		double h0 = iconImg.getHeight(null);
		
		double h1 = h*H_FACTOR;
		double w1 = h1*w0/h0;
		
		g.drawImage(iconImg, (int) (w-w1), (int) (h-h1), (int) w1, (int) h1, null);
		g.dispose();
		
		return image;
	}
}